(ns calabash-script-example.core
  (:require [calabash-script.log :as log]
            [calabash-script.utils :as utils])
  (:use [calabash-script.core :only
         [query tap tap-mark sleep screenshot
          keyboard-enter-text enter]]

        [calabash-script.tests :only  [define-uia-test run-all!]]))

(define-uia-test
  "Archery details should be accessible via Events"
  (fn []
    (tap-mark "Events")
    (utils/screenshot "Events")
    (sleep 3)

    ))

(run-all!)
