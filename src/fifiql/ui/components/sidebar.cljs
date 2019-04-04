(ns fifiql.ui.component.sidebar
  (:require
   [re-frame.core :as re]
   [reagent.core :as r]
   [fifiql.ui.subs :as ui.subs]
   [fifiql.ui.events :as ui.events]
   [fifiql.ui.utf :refer [icon-unexpand icon-expand]]))


(defn group-listing [group words]
  (let [word-info (re/subscribe [::ui.subs/word-info])
        open? (r/atom false)]
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


(defn search-listing []
  (let [word-listing (re/subscribe [::ui.subs/word-listing])]
    (fn []
      (let [groupings (group-by :group @word-listing)]
        [:div.search-listing
         (doall
          (for [[group words] (sort groupings)]
            [group-listing group words]))]))))


(defn word-info []
  (let [word-meta (re/subscribe [::ui.subs/word-meta])]
    (fn []
      (when @word-meta
        [:<>
         [:div.name (pr-str (:name @word-meta))]
         [:div.group (pr-str (:group @word-meta))]
         [:div.documentation (:doc @word-meta)]]))))
