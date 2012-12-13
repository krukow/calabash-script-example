(ns calabash-script-example.core
  (:require [calabash-script.log :as log]
            [calabash-script.utils :as utils])
  (:use [calabash-script.core :only
         [query tap tap-mark sleep screenshot
          scroll-to
          keyboard-enter-text enter]]

        [calabash-script.tests :only  [define-uia-test fail run-all!]]))

(define-uia-test
  "Archery details should be accessible via Events"
  (fn []
    (tap-mark "Events")
    (utils/screenshot "Events")
    (sleep 3)
    (scroll-to [:view {:marked "water polo"}])
    (utils/screenshot "Events")
    (tap-mark "water polo")
    (sleep 2)
    (when-not (seq (query [:view
                           {:marked "Olympic Water Polo, past and present"}]))
      (fail "Olympic Water Polo, past and present"))
    (utils/screenshot "Water polo details")))

(run-all!)
