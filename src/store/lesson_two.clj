(ns store.lesson-two)


;; Function with override params
(defn my-count
  ([elements]
   (my-count 0 elements))

  ([total-so-far elements]
   (if (seq elements)
     (recur (inc total-so-far) (rest elements))
     total-so-far))

  )


(println "First with one param"(my-count [2 3 4 5]))
(println "And with two params"(my-count 10 [2 3 4 5]))