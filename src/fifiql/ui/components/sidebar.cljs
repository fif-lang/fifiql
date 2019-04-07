(ns fifiql.ui.component.sidebar
  (:require
   [clojure.string :as str]
   [re-frame.core :as re]
   [reagent.core :as r]
   [fifiql.ui.subs :as ui.subs]
   [fifiql.ui.events :as ui.events]
   [fifiql.ui.utf :refer [icon-unexpand icon-expand]]))


(defn group-listing [group words]
  (let [word-info (re/subscribe [::ui.subs/word-info])
        open? (r/atom open?)]
    (fn [group words]
      [:div.group-container {:key (str "container-" group)}
       [:div.group {:key (str "group-" group)
                    :on-click #(swap! open? not)}
        [:div.icon (if @open? icon-expand icon-unexpand)]
        [:div.label (str (or group "unassigned") " (" (count words) ")")]]
       (when @open?
         (doall
          (for [word (sort-by :name words)]
            [:div.word
             {:class (when (= @word-info (:name word)) "active")
              :key (str "word-" (:name word))
              :on-click #(re/dispatch [::ui.events/choose-word-info (:name word)])}
             (pr-str (:name word))])))])))


(defn- process-search-query [search-string word-listing]
  (if-not (empty? search-string)
    (filter (fn [{:keys [group name]}]
              (or (str/includes? (str group) search-string)
                  (str/includes? (str name) search-string)))
            word-listing)
    word-listing))


(defn- process-word-listing [word-listing stdlib? search-string]
  (if stdlib?
    (group-by :group word-listing)
    (->> word-listing
         (filter #(not (:stdlib? %)))
         (process-search-query search-string)
         (group-by :group))))


(defn search-listing []
  (let [toggle-stdlib? (re/subscribe [::ui.subs/toggle-stdlib?])
        word-listing (re/subscribe [::ui.subs/word-listing])
        search-string (re/subscribe [::ui.subs/search-string])]
    (fn []
      (let [groupings (process-word-listing @word-listing @toggle-stdlib? @search-string)]
        [:div.search-listing
         (doall
          (for [[group words] (sort groupings)]
            [group-listing group words]))]))))


(defn word-info []
  (let [word-meta (re/subscribe [::ui.subs/word-meta])]
    (fn []
      (if @word-meta
        [:<>
         [:div.name (pr-str (:name @word-meta))]
         [:div.group (pr-str (:group @word-meta))]
         [:div.documentation (:doc @word-meta)]]
        [:div.empty "Word Info"]))))
