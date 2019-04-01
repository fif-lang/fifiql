(ns fifiql.dev.user
  (:require
   [mount.core :as mount :refer [defstate]]
   [fifiql.dev.server]))


(defn start []
  (mount/start))


(defn stop []
  (mount/stop))


(defn restart []
  (stop)
  (start))

