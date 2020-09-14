(ns hospital.logic-test
  (:require [clojure.test :refer :all]
            [hospital.logic :refer :all]))

(def empty_queue clojure.lang.PersistentQueue/EMPTY)

(deftest on-full-hospital?
  (testing "Should return false when the hospital is empty"
    (is (not (full-hospital? {:queue empty_queue} :queue))))

  (testing "Should return false when the hospital has space to someone"
    (is (not (full-hospital? {:queue [1 2 3]} :queue))))

  (testing "Should return true when hhe hospital is full"
    (is (full-hospital? {:queue [ 1 2 3 4 5]} :queue)))

  (testing "Should return false when there's no departament"
    (is (not (full-hospital? {:some-departament [1 2 3 4]} :queue)))))

