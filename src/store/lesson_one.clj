(ns store.lesson-one)

;; call my map using Tail Recursion
(defn my-map
  [function-to-apply elements]
    (let [first-value (first elements)]
             (if (not (nil? first-value))
               (do
                   (function-to-apply first-value)
                   (recur function-to-apply (rest elements))))))

;; Function that will be passed to my-map
(defn print-value [value] (println value))

;; my-map call
(my-map print-value (range 100000))