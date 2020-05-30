(ns hospital.collections
  (:use [clojure pprint])
  (:require [hospital.logic :as h.logic]
            [hospital.model :as h.model]))

(defn simulates-one-day []
  "Simulates one day in a hospital"
  (def hospital (h.model/new-hospital))
  (pprint (h.logic/arrives-in hospital :queue "222"))

  (def hospital (h.logic/attend hospital :laboratory-1))

  (pprint hospital))

(defn arrives-in-with-bad-pratices!
  [hospital people]
  (swap! hospital h.logic/arrives-in-with-delay-and-log :queue people)
  (println "After insert the people" people))

#_(simulates-one-day)
(defn simulates-one-day-in-parallel
  []
  "Simulates one day using threads"
  (let [hospital (atom (h.model/new-hospital))]
    (.start (Thread. (fn [] (arrives-in-with-bad-pratices! hospital "111"))))
    (.start (Thread. (fn [] (arrives-in-with-bad-pratices! hospital "222"))))
    (.start (Thread. (fn [] (arrives-in-with-bad-pratices! hospital "333"))))
    (.start (Thread. (fn [] (arrives-in-with-bad-pratices! hospital "444"))))
    (.start (Thread. (fn [] (arrives-in-with-bad-pratices! hospital "555"))))
    (.start (Thread. (fn [] (arrives-in-with-bad-pratices! hospital "666"))))
    (.start (Thread. (fn [] (Thread/sleep 4000)
                       (pprint hospital))))))

(simulates-one-day-in-parallel)