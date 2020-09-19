(ns hospital.logic-test
  (:require [clojure.test :refer :all]
            [hospital.logic]))

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

