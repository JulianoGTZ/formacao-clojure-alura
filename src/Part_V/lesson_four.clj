(ns Part-V.lesson-four
  (:use clojure.pprint)
  (:require [schema.core :as s]))

(s/set-fn-validation! true)

(def PostInt (s/pred pos-int? 'positive-integer))
(def Plan [s/Keyword])
(def Patient
  {:id                          PostInt
   :name                        s/Str
   :plan                        Plan
   (s/optional-key :birth-date) s/Str})
(def Patients {PostInt Patient})

(pprint (s/validate Patient {:id 20 :name "Roadrigoan" :plan [:x-ray]}))

(let [john {:id 20 :name "Roadrigoan" :plan [:x-ray]}]
  (pprint (s/validate Patients {15 john})))
