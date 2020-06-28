(ns Part-V.lesson-three
  (:use clojure.pprint)
  (:require [schema.core :as s]))

(s/set-fn-validation! true)

;; rules
(def PostInt (s/pred pos-int?))
(defn MoreOrEqualToZero?
  [x]
  (>= x 0))

(def FinancialValue (s/constrained s/Num MoreOrEqualToZero?))

(def Plan [s/Keyword])
(def Patient
  {:id   PostInt
   :name s/Str
   :plan Plan})

(s/defn new-patient :- Patient
  [id :- PostInt
   name :- s/Str
   plan :- Plan]
  {:id id :name name :plan plan})

(pprint (new-patient 15 "John" [:x-ray]))

(def Order
  {:patient   Patient
   :value     FinancialValue
   :procedure s/Keyword})

(s/defn new-order :- Order
  [patient :- Patient
   value :- FinancialValue
   procedure :- s/Keyword]
  {:patient patient :value value :procedure procedure})

(pprint (new-order (new-patient 10 "Juanes" [:plan]) 15.10 :x-ray))

(pprint (s/validate Plan [:x-ray]))

(pprint (s/validate Patient {:id 20 :name "Roadrigoan" :plan [:x-ray]}))