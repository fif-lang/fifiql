(ns fifiql.ui.component.sidebar
  (:require
   [re-frame.core :as re]
   [fifiql.ui.subs :as ui.subs]))


(defn search-listing []
  (let [word-listing (re/subscribe [::ui.subs/word-listing])]
    (fn []
      (let [groupings (group-by :group @word-listing)]
        [:div.search-listing
         (doall
          (for [[group _] groupings]
            [:div.group group]))]))))
