(ns store.lesson-one)

;; call my map
(defn my-map
  [function-to-apply elements]
  (do
    (let [val (first elements)]
             (if (not (nil? val))
               (do (function-to-apply val)
                   (my-map function-to-apply (rest elements)))))))

(def elements [10 20 30 50])

(defn print-value [value] (println value))

(my-map print-value elements)