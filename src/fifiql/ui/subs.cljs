(ns fifiql.ui.subs
  (:require
   [re-frame.core :as re :include-macros true]))


(re/reg-sub
 ::word-listing
 (fn [db _]
   (:word-listing db)))
