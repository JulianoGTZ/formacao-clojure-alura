(ns hospital.collections
  (:use [clojure pprint])
  (:require [hospital.model :as h.model]
            [hospital.logic :as h.logic]))

(defn simulates-one-day []
  "Simulates one day in a hospital"
  (def hospital (h.model/new-hospital))
  (pprint (h.logic/arrives-in hospital :queue "222"))

  (def hospital (h.logic/attend hospital :laboratory-1))

  (pprint hospital))

(simulates-one-day)

