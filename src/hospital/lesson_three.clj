(ns hospital.lesson-three
  (:use [clojure pprint])
  (:require [hospital.logic :as h.logic]
            [hospital.model :as h.model]))

(def name "Jonas Brother")

(defn test-atom []
  (let [lima-hospital (atom {:queue h.model/empty_queue})]
    (println lima-hospital)
    (pprint lima-hospital)
    (pprint (deref lima-hospital))

    ;; Wrong way to change the content of an atom
    (pprint (assoc @lima-hospital :laboratory-1 h.model/empty_queue))
    (pprint @lima-hospital)

    ;; Changing a atom value correctly
    (swap! lima-hospital assoc :laboratory-1 h.model/empty_queue)
    (pprint lima-hospital)

    ;; Swap with update
    (swap! lima-hospital update :laboratory-1 conj "111")))

(test-atom)
