(ns minimal.core
  (:require [ring.adapter.jetty :as jetty]
            #_[aleph.http         :as aleph]
            [clojure.pprint     :as pprint]))

(defonce server (atom nil))

(defn app [req]
  {:status 200
   :body (with-out-str (pprint/pprint req))
   :headers {"Content-Type" "text/plain"}})

(defn start-server []
  (reset! server
          #_(aleph/start-server (fn [req] (app req)) {:port 3001})
          (jetty/run-jetty
           (fn [req] (app req))
           {:port 3001       ;; listen on port 3001
            :join? false}))) ;; don't block the main thread

(defn stop-server []
  (when-some [s @server]
    ;;(.close s)
    (.stop s)
    (reset! server nil)))
