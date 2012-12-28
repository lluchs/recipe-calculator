(ns recipe-calculator.solver-test
  (:use clojure.test
        recipe-calculator.solver))

(deftest stock-empty-test
  (testing "filled stocks"
    (is (not (stock-empty? {:foo 0 :bar 0 :baz 23})))
    (is (not (stock-empty? {:foo 1 :bar 0 :baz 0}))))
  (testing "empty stock"
    (is (stock-empty? {:foo 0 :bar 0 :baz 0}))))

(deftest stock-valid-test
  (testing "valid stocks"
    (is (stock-valid? {:foo 0 :bar 0 :baz 23}))
    (is (stock-valid? {:foo 0 :bar 0 :baz 0})))
  (testing "invalid stocks"
    (is (not (stock-valid? {:foo -1 :bar 12 :baz 34})))))

(deftest bake-test
  (are [stock recipe result] (= result (bake stock recipe))
       {:butter 200 :zucker 200 :mehl 500 :eier 3 :mandeln 200} :buttertilden
       {:butter   0 :zucker   0 :mehl   0 :eier 0 :mandeln 200}
       {:butter 200 :zucker 200 :mehl 500 :eier 3 :mandeln 200} :pisterne
       {:butter   0 :zucker  20 :mehl 500 :eier 1 :mandeln -200}))
