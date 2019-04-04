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


(re/reg-event-fx
 ::choose-word-info
 (fn [{:keys [db]} [_ word]]
   {:db (assoc db :word-info word)}))


(re/reg-event-fx
 ::change-editor-text
 (fn [{:keys [db]} [_ text]]
   {:db (assoc db :editor-text text)}))


(re/reg-event-fx
 ::run-query
 (fn [{:keys [db]} [_ sform]]
  {:fifql/query
    {:url "/fifql"
     :sform sform
     :on-success ::process-query
     :on-failure ::process-query}}))


(re/reg-event-fx
 ::process-query
 (fn [{:keys [db]} [_ result]]
   (println result)
   {:db (assoc db
               :result-stack (:stack result)
               :result-stdout (:stdout result)
               :result-stderr (:stderr result))}))
                    
