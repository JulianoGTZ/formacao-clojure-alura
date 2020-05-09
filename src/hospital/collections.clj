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

#_(simulates-one-day)

(defn simulates-one-day-in-parallel
  []
  "Simulates one day using threads"
  (def hospital (h.model/new-hospital))
  (.start (Thread. (fn [] (h.logic/arrives-in hospital :queue "111"))))
  (.start (Thread. (fn [] (h.logic/arrives-in hospital :queue "222"))))
  (.start (Thread. (fn [] (h.logic/arrives-in hospital :queue "333"))))
  (.start (Thread. (fn [] (h.logic/arrives-in hospital :queue "444"))))
  (.start (Thread. (fn [] (h.logic/arrives-in hospital :queue "555"))))
  (.start (Thread. (fn [] (h.logic/arrives-in hospital :queue "666")))))

(simulates-one-day-in-parallel)