(ns fifiql.dev.server
  (:require
   [mount.core :as mount :refer [defstate]]

   ;; High-performance Web Server
   [org.httpkit.server :as httpkit]

   ;; Routing Library
   [compojure.core :refer [GET POST defroutes]]
   [compojure.route :as route]
   [ring.middleware.resource :refer [wrap-resource]]
   [ring.middleware.defaults :refer [wrap-defaults site-defaults]]

   ;; Fifql Library
   [fifql.server :refer [create-ring-request-handler]]
   [fifql.core :as fifql]

   ;; Main
   [fifiql.page]))


(def server-name "A fifql Example Server")
(def server-port 8080)


;; Create our stack machine, and define some word functions
(def stack-machine
  (-> (fifql/create-stack-machine)

      (fifql/set-word 'add2 (fifql/wrap-function 1 (fn [x] (+ 2 x)))
       :doc "(n -- n) Add 2 to the value"
       :group :fifql.example)

      (fifql/set-var 'server-details {:server-port server-port :server-name server-name}
       :doc "The server details"
       :group :fifql.example)))


;; Create our ring request handler to use with Httpkit
(def fifql-handler
  (create-ring-request-handler
   :prepare-stack-machine stack-machine))


;; Create our routes. The fifql ring handler supports both GET and POST requests
(defroutes app-routes
  (GET "/" req "Hello World!")
  (GET "/fifql" req fifql-handler)
  (POST "/fifql" req fifql-handler)
  (GET "/fifiql" req (fifiql.page/main)))


(def site-config
  (-> site-defaults
      (assoc-in [:security :anti-forgery] false)))


(def app
  (-> app-routes
      (wrap-defaults site-config)))


(defn start []
  (println "Starting server on port: " server-port "...")
  (httpkit/run-server #'app {:port server-port}))


(defstate http-server
   :start (start)
   :stop (http-server))


(defn -main [& args]
  (mount/start))
