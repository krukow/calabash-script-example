(ns calabash-script-example.repl
  (:require [cljs.repl.uia :as uia]
            [calabash-script.clj.run :as run]))

(defn start-repl
  [path]
  (uia/run-uia-repl :app path))

(defn run-tests
  []
  (run/run-test
   :app "/Users/krukow/github/2012-Olympics-iOS--iPad-and-iPhone--source-code/2012 Olympics/build/Release-iphonesimulator/2012 Olympics.app"
   :test "build/test_script.js"))
