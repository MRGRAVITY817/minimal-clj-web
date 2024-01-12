(ns minimal.core
  (:require [ring.adapter.jetty :as jetty]))

(defonce server (atom nil))

(defn start-server []
  (reset! server
          (jetty/run-jetty
           (fn [_req] {:status 200 :body "Hello" :headers {}})
           {:port 3001       ;; listen on port 3001
            :join? false}))) ;; don't block the main thread

(defn stop-server []
  (when-some [s @server]
    (.stop s)
    (reset! server nil)))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
