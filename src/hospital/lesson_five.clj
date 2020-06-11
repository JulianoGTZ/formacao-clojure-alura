(ns hospital.lesson-five
  (:use [clojure pprint])
  (:require [hospital.logic :as h.logic]
            [hospital.model :as h.model]))

(defn arrives-in!
  [hospital people]
  (swap! hospital h.logic/arrives-in-with-delay-and-log :queue people))

(defn transfer!
  [hospital from-department to-department]
  (swap! hospital h.logic/transfer from-department to-department))

(defn simulate-one-day
  []
  (let [hospital (atom (h.model/new-hospital))]
    (arrives-in! hospital "john")
    (arrives-in! hospital "maria")
    (arrives-in! hospital "daniela")
    (transfer! hospital :queue :laboratory-1)
    (pprint hospital)))

(simulate-one-day)