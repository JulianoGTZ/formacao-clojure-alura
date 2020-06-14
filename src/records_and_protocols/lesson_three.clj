(ns records-and-protocols.lesson-three
  (:use clojure.pprint)
  (:require [records-and-protocols.logic :as r.logic]))

(defn load-patient
  [id]
  (println "Loading" id)
  (Thread/sleep 1000)
  {:id id :loaded-when (r.logic/now)})

(defn load-when-is-not-cached
  [cache id loader]
  (if (contains? cache id)
    cache
    (let [patient (loader id)]
      (assoc cache id patient))))

(defprotocol Loadable
  (load! [this id]))

(defrecord Cache
           [cache loader]
  Loadable
  (load! [this id]
    (swap! cache load-when-is-not-cached id loader)
    (get @cache id)))

(def patients (->Cache (atom {}) load-patient))
(pprint patients)
(load! patients 15)
(pprint patients)