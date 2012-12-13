(ns calabash-script-example.repl
  (:require [cljs.repl.uia :as uia]
            [calabash-script.clj.run :as run]))

(defn start-repl
  []
  (uia/run-uia-repl :app (System/getenv "APP")))

(defn run-tests
  []
  (run/run-test
   :app (System/getenv "APP")
   :test "build/test_script.js"))
