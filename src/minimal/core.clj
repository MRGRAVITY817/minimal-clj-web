(ns minimal.core
  (:require [ring.adapter.jetty :as jetty]))

(defonce server (atom nil))

(defn app [_req]
  {:status 200 :body "Hhhhhh" :headers {}})

(defn start-server []
  (reset! server
          (jetty/run-jetty
           (fn [req] (app req))
           {:port 3001       ;; listen on port 3001
            :join? false}))) ;; don't block the main thread

(defn stop-server []
  (when-some [s @server]
    (.stop s)
    (reset! server nil)))
