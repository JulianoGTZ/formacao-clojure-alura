(ns hospital.model
  (:require [schema.core :as s]))

(def empty_queue clojure.lang.PersistentQueue/EMPTY)

(defn new-hospital []
  {:queue        empty_queue
   :laboratory-1 empty_queue
   :laboratory-2 empty_queue
   :laboratory-3 empty_queue})

(s/def Patient {:id s/Str})
(s/def Department (s/queue Patient))
(s/def Hospital {s/Keyword Department})

(defn new-department
  []
  empty_queue)
