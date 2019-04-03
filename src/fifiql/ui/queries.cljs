(ns fifiql.ui.queries
  (:require
   [fifql.client :refer [sform] :include-macros true]))


(defn get-word-listing []
  (sform list-words))
