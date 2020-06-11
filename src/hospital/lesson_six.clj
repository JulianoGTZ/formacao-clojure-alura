(ns hospital.lesson-six
  (:use     [clojure pprint])
  (:require [hospital.model :as h.model]))

(defn arrives-in
  [line person]
  (conj line person))

(defn arrives-in-by-refset-function!
  [hospital person]
  (let [line (get hospital :queue)]
    (ref-set line (arrives-in (deref line) person))))

(defn arrives-in-by-alter-function!
  [hospital person]
  (let [line (get hospital :queue)]
    (alter line arrives-in person)))

(defn simulate-one-day
  []
  (let [hospital {:queue        (ref h.model/empty_queue)
                  :laboratory-1 (ref h.model/empty_queue)
                  :laboratory-2 (ref h.model/empty_queue)
                  :laboratory-3 (ref h.model/empty_queue)}]
    (dosync
     (arrives-in-by-alter-function! hospital "Little John"))
    (pprint hospital)))

(simulate-one-day)
