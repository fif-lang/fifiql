(ns fifiql.core
  (:require
   [fifiql.page]))



(defn handle-request
  "Main request handler for fifiql

  Optional Arguments:

  fifiql-source-path -- Path to the fifiql source file, which can
  either be compiled separately, or compiled into your main javascript
  asset. Default is /js/compiled/fifiql.js"
  [& [{:keys [fifiql-source-path]
       :or {fifiql-source-path "/js/compiled/fifiql.js"}}]]
  (fifiql.page/main fifiql-source-path))
