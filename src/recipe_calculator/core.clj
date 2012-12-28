(ns recipe-calculator.core
  (:gen-class)
  (:require [recipe-calculator.solver :as solver]))

(defn -main
  "Runs the calculation."
  [& args]
  (let [stock {:butter 6500
               :zucker 9000
               :mehl 18000
               :eier 195
               :mandeln 775}
        solutions (solver/solve stock)]
    (doseq [solution solutions]
      (println solution))))
