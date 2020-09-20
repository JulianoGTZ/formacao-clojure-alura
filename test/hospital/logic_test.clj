(ns hospital.logic-test
  (:require [clojure.test :refer :all]
            [hospital.logic]
            [hospital.model :as h.model]
            [schema.core :as s]))

(s/set-fn-validation! true)
(def empty_queue clojure.lang.PersistentQueue/EMPTY)

(deftest on-full-hospital?
  (testing "Should return false when the hospital is empty"
    (is (not (hospital.logic/full-hospital? {:queue empty_queue} :queue))))

  (testing "Should return false when the hospital has space to someone"
    (is (not (hospital.logic/full-hospital? {:queue [1 2 3]} :queue))))

  (testing "Should return true when hhe hospital is full"
    (is (hospital.logic/full-hospital? {:queue [1 2 3 4 5]} :queue)))

  (testing "Should return false when there's no departament"
    (is (not (hospital.logic/full-hospital? {:some-departament [1 2 3 4]} :queue)))))

(deftest arrive-in-test
  (testing "Accepts people while fitting people in line"
    (let [updated-hospital {:queue [1 2 3 4 5]}]
      (is updated-hospital
          (hospital.logic/arrives-in {:queue [1 2 3 4]} :queue 5))))

  (testing "Should not allow the insertion when the hospital is full"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #"Hospital is full"
                          (hospital.logic/arrives-in {:queue [1 2 3 4 5]} :queue 6)))))

(deftest on-transfer
  (testing "Should transfer people when its possible"
    (let [patient  {:id "5"}
          hospital {:queue (conj h.model/empty_queue patient) :x-ray hospital.model/empty_queue}]
      (is (= {:queue hospital.model/empty_queue :x-ray [patient]}
             (hospital.logic/transfer hospital :queue :x-ray)))))

  (testing "Should transfer people in the right order. First-in, First-out"
    (let [john     {:id "5"}
          maria    {:id "51"}
          afonso   {:id "13"}
          hospital {:queue (conj h.model/empty_queue maria john) :x-ray (conj h.model/empty_queue afonso)}]
      (is (= {:queue [john] :x-ray [afonso maria]}
             (hospital.logic/transfer hospital :queue :x-ray)))))
  (testing "Should not transfer people when its impossible"
    (let [john     {:id "5"}
          hospital {:queue (conj h.model/empty_queue john) :x-ray (conj h.model/empty_queue {:id "1"} {:id "2"} {:id "4"} {:id "5"} {:id "6"})}]
      (is (thrown-with-msg? clojure.lang.ExceptionInfo #"Hospital is full"
                            (hospital.logic/transfer hospital :queue :x-ray))))))
