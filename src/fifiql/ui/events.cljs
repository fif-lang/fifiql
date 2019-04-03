(ns fifiql.ui.events
  (:require
   [re-frame.core :as re]
   [fifql-fx.effects]
   [fifiql.ui.queries :refer [get-word-listing]]))


(re/reg-event-fx
 ::init
 (fn [{:keys [db]} _]
   {:fifql/query
    {:url "/fifql"
     :sform (get-word-listing)
     :on-success ::process-word-listing}}))


(re/reg-event-fx
 ::process-word-listing
 (fn [{:keys [db]} [_ {:keys [stack]}]]
   (let [word-listing (first stack)]
     {:db (assoc db :word-listing word-listing)})))
