(ns hospital.collections
  (:use [clojure pprint]))

(defn test-vector
  []
  (let [queue [111 222]]
    (println (conj queue 333))))

(test-vector)

(defn test-queue []
  ;;Creating a queue
  (let [queue (conj clojure.lang.PersistentQueue/EMPTY "111" "222")]
  (println "Queue")
  (pprint queue)))

(test-queue)