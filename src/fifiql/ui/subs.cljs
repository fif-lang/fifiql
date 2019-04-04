(ns fifiql.ui.subs
  (:require
   [re-frame.core :as re :include-macros true]))


(re/reg-sub
 ::word-listing
 (fn [db _]
   (:word-listing db)))


(re/reg-sub
 ::word-info
 (fn [db _]
   (:word-info db)))


(re/reg-sub
 ::word-meta
 :<- [::word-listing]
 :<- [::word-info]
 (fn [[word-listing word-info]]
   (first (filter #(= (:name %) word-info) word-listing))))


(re/reg-sub
 ::editor-text
 (fn [db _]
   (:editor-text db)))
