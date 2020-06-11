(ns hospital.lesson-four
  (:use [clojure pprint])
  (:require [hospital.logic :as h.logic]
            [hospital.model :as h.model]))

(defn simulates-one-day []
  "Simulates one day in a hospital"
  (def hospital (h.model/new-hospital))
  (pprint (h.logic/arrives-in hospital :queue "222"))

  (def hospital (h.logic/attend hospital :laboratory-1))

  (pprint hospital))

(defn arrives-in-with-bad-practices!
  [hospital people]
  (swap! hospital h.logic/arrives-in :queue people)
  (println "After insert the people" people))

(defn start-thread!
  ([hospital person]
   (.start (Thread. (fn [] (arrives-in-with-bad-practices! hospital person))))))

(defn simulates-one-day-in-parallel
  []
  "Simulates one day using threads"
  (let [hospital      (atom (h.model/new-hospital))
        people        ["111" "222" "333" "444" "555" "666"]
        start-queue!  (partial start-thread! hospital)]
    (mapv start-queue! people)
    (.start (Thread. (fn [] (Thread/sleep 4000)
                       (pprint hospital))))))

(defn simulates-one-day-in-parallel-with-doseq
  []
  "Simulates one day using threads"
  (let [hospital      (atom (h.model/new-hospital))
        people        ["111" "222" "333" "444" "555" "666"]]
    (doseq [person people]
      (start-thread! hospital person))

    (.start (Thread. (fn [] (Thread/sleep 4000)
                       (pprint hospital))))))

(defn simulates-one-day-in-parallel-with-dotimes
  []
  "Simulates one day using threads"
  (let [hospital      (atom (h.model/new-hospital))
        people        ["111" "222" "333" "444" "555" "666"]]
    (dotimes [person 6]
      (start-thread! hospital person))

    (.start (Thread. (fn [] (Thread/sleep 4000)
                       (pprint hospital))))))

(simulates-one-day-in-parallel-with-dotimes)