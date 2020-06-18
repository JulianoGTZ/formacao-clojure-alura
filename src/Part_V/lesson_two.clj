(ns Part-V.lesson-two
  (:use clojure.pprint)
  (:require [schema.core :as s]))

(s/set-fn-validation! true)

(defn positive-number?
  [x]
  (> x 0))

(def StrictlyPositive (s/pred positive-number? 'strictly-positive))

(def Patient
  "Patient schema"
  {:id   (s/constrained s/Int StrictlyPositive)
   :name s/Str})

(s/defn new-patient :- Patient
  [id :-  (s/constrained s/Int pos?)
   name :- s/Str]
  {:id id :name name})

(new-patient 1 "20")
