(defproject calabash-script-example "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [org.clojure/clojurescript "0.0-1450"]
                 [calabash-script "0.1.0-SNAPSHOT"]]

  :plugins [[lein-cljsbuild "0.2.9"]]
  :cljsbuild {:builds
              [{:source-path "src"
                :jar true
                :compiler {:output-to "build/test_script.js"
                           :optimizations :whitespace
                           :pretty-print true}}]})
