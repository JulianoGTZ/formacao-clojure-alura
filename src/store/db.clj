(ns store.db)

(def order1 {:user  15
             :items {:bag    {:id :bag :amount 12 :unit-price 80}
                     :shirt  {:id :shirt :amount 44 :unit-price 45}
                     :tennis {:id :tennis :amount 31}}})

(def order2 {:user  15
             :items {:bag    {:id :bag :amount 33 :unit-price 80}
                     :shirt  {:id :shirt :amount 4 :unit-price 45}
                     :tennis {:id :tennis :amount 21}}})

(def order3 {:user  17
             :items {:bag    {:id :bag :amount 4 :unit-price 80}
                     :shirt  {:id :shirt :amount 4 :unit-price 45}
                     :tennis {:id :tennis :amount 51}}})
(def order4 {:user  15
             :items {:bag    {:id :bag :amount 22 :unit-price 80}
                     :shirt  {:id :shirt :amount 4 :unit-price 45}
                     :tennis {:id :tennis :amount 66}}})
(def order5 {:user  10
             :items {:bag    {:id :bag :amount 21 :unit-price 80}
                     :shirt  {:id :shirt :amount 4 :unit-price 45}
                     :tennis {:id :tennis :amount 11}}})
(def order6 {:user  21
             :items {:bag    {:id :bag :amount 12 :unit-price 80}
                     :shirt  {:id :shirt :amount 44 :unit-price 45}
                     :tennis {:id :tennis :amount 10}}})
(def order7 {:user  35
             :items {:bag    {:id :bag :amount 3 :unit-price 80}
                     :shirt  {:id :shirt :amount 5 :unit-price 45}
                     :tennis {:id :tennis :amount 4}}})

(defn all-orders []
  [order1 order2 order3 order4 order5 order6 order7])