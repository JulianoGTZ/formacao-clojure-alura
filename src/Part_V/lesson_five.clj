(ns Part-V.lesson-five
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

(def Visits {PostInt [s/Str]})

(s/defn add-patient :- Patients
  [patients :- Patients
   patient :- Patient]
  (if-let [id (:id patient)]
    (assoc patients id patient)
    (throw (ex-info "Patient doesn't have id" {:patient patient}))))

(s/defn add-visit :- Visits
  [visits :- Visits
   patient :- PostInt
   newVisits :- [s/Str]]
  (if (contains? visits patient)
    (update visits patient concat newVisits)
    (assoc visits patient newVisits)))

(s/defn print-report
  [visits :- Visits
   patient :- PostInt]
  (println "Visits of the patient" patient "are" visits))

(defn test-patient-use []
  (let [john     {:id 25 :name "John" :plan []}
        mary     {:id 25 :name "Mary" :plan []}
        jhonny   {:id 25 :name "Johnny" :plan []}
        patients (reduce add-patient {} [john mary jhonny])]
    (print-report (-> {}
                      (add-visit 21 ["01/01/2010"])
                      (add-visit 22 ["01/01/2010"])
                      (add-visit 23 ["01/01/2010"])) 25)))

(test-patient-use)

