(ns recipe-calculator.solver)

(def recipes {:buttertilden {:butter 200
                             :zucker 200
                             :mehl 500
                             :eier 3}
              :schneewittchenglück {:butter 150
                                    :zucker 200
                                    :mehl 200
                                    :eier 3
                                    :mandeln 350}
              :pisterne {:butter 200
                         :zucker 180
                         :mandeln 400
                         :eier 2}
              :phikringel {:butter 100
                           :zucker 200
                           :mehl 600
                           :eier 7
                           :mandeln 50}
              :wurzelklumpen {:mehl 500
                              :kakao 100
                              :eier 1}})

(defn stock-empty?
  "Decides whether the given stock is empty."
  [stock]
  (not-any? #(> % 0) (vals stock)))

(defn stock-valid?
  "Decides whether the given stock is valid, i.e. there are no negative things in it."
  [stock]
  (not-any? #(< % 0) (vals stock)))

(defn bake
  "Returns the stock after baking the given recipe."
  [stock recipe]
  (let [ingredients (recipes recipe)
        result (map (fn [[ingredient amount]]
                      [ingredient (- amount (or (ingredients ingredient) 0))])
                    stock)]
    (apply hash-map (flatten result))))

(defn solve
  "Decides which recipes are needed to clear the stock."
  ([stock]
    (map frequencies (solve stock (keys recipes))))
  ([stock untested-recipes]
    (if (stock-empty? stock)
      [[]]
      (apply concat
        (for [recipe untested-recipes
              :let [new-stock (bake stock recipe)]
              :when (stock-valid? new-stock)]
          (map #(conj % recipe)
               (solve new-stock (drop-while #(not (= recipe %)) untested-recipes))))))))
