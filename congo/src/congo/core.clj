(ns congo.core
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            [taoensso.tufte :as tufte :refer (defnp p profiled profile)]
            [clojure.data.generators :as gen])
  (:import [com.mongodb MongoOptions ServerAddress])
  (:gen-class))
(defn demo [] (rand-int 1000))
;;;; close mongodb log..
(.setLevel 
  (java.util.logging.Logger/getLogger "org.mongodb.driver") 
  (java.util.logging.Level/SEVERE))
(defn mongo-test [index max-times]
  (let [conn (mg/connect)
        db   (mg/get-db conn "monger-test")]
    (dotimes [_ max-times]
       (let [name (gen/string)
             age (gen/int)] 
          (p :mongo-insert (mc/insert db "clojure" {:name name :age age}))
          (swap! index inc)))
    (mg/disconnect conn)))
(def max-times 10000) 
(defn -main
  [& args]
  (def index (atom 0))
  (tufte/add-basic-println-handler! {})
  (profile {}
        (mongo-test index max-times))
  ;; 阻塞主线程,直到执行完毕后退出
  (while (> max-times  @index))
  (Thread/sleep 200))
  
    

  
