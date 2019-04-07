(ns fifiql.ui.component.editor
  (:require
   [clojure.string :as str]
   [re-frame.core :as re]
   [reagent.core :as r]
   [fifiql.ui.subs :as ui.subs]
   [fifiql.ui.events :as ui.events]))


(defn editor []
  [:textarea.editor-area
   {:on-change #(re/dispatch [::ui.events/change-editor-text (-> % .-target .-value)])}])


(defn stack-info []
  (let [result-stack (re/subscribe [::ui.subs/result-stack])]
    [:div.container
     (if-not (empty? @result-stack)
       (doall
        (for [value @result-stack]
          [:div.value {:key (str "stack-" value)} (pr-str value)]))
       [:div.empty "STACK"])]))


(defn stdout-info []
  (let [result-stdout (re/subscribe [::ui.subs/result-stdout])]
    [:div.container
     (if-not (empty? @result-stdout)
       (doall
        (for [value-coll @result-stdout]
          (for [value (str/split value-coll #"\n")]
            [:div.value {:key (str "stdout-" value)} value])))
       [:div.empty "STDOUT"])]))


(defn stderr-info []
  (let [result-stderr (re/subscribe [::ui.subs/result-stderr])]
    [:div.container
     (if-not (empty? @result-stderr)
       (doall
        (for [value-coll @result-stderr]
          (for [value (str/split value-coll #"\n")]
            [:div.value {:key (str "stderr-" value)} value])))
       [:div.empty "STDERR"])]))
