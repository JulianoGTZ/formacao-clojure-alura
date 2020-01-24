(ns store.db)
(def order1 { :user 15
             :items {:bag {:id :bag :amount 2 :unit-price 80}}
             :items {:shirt {:id :shirt :amount 3 :unit-price 20}}
             :items {:tennis {:id :tennis :amount 3}}
             })

(def order2 { :user 10
             :items {:bag {:id :bag :amount 2 :unit-price 80}}
             :items {:shirt {:id :shirt :amount 3 :unit-price 20}}
             :items {:tennis {:id :tennis :amount 3}}
             })

(def order3 { :user 11
             :items {:bag {:id :bag :amount 2 :unit-price 80}}
             :items {:shirt {:id :shirt :amount 3 :unit-price 20}}
             :items {:tennis {:id :tennis :amount 3}}
             })

(def order4 { :user 12
             :items {:bag {:id :bag :amount 22 :unit-price 80}}
             :items {:shirt {:id :shirt :amount 3 :unit-price 20}}
             :items {:tennis {:id :tennis :amount 3}}
             })

(def order5 { :user 30
             :items {:bag {:id :bag :amount 12 :unit-price 80}}
             :items {:shirt {:id :shirt :amount 3 :unit-price 20}}
             :items {:tennis {:id :tennis :amount 3}}
             })

(def order6 { :user 20
             :items {:bag {:id :bag :amount 43 :unit-price 80}}
             :items {:shirt {:id :shirt :amount 3 :unit-price 20}}
             :items {:tennis {:id :tennis :amount 3}}
             })

(def order7 { :user 19
             :items {:bag {:id :bag :amount 33 :unit-price 800}}
             :items {:shirt {:id :shirt :amount 3 :unit-price 20}}
             :items {:tennis {:id :tennis :amount 3}}
             })

(defn all-orders []
  [order1 order2 order3 order4 order5 order6 order7])