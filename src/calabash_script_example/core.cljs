(ns calabash-script-example.core
  (:require [calabash-script.log :as log]
            [calabash-script.utils :as utils])
  (:use [calabash-script.core :only
         [define-uia-test query tap sleep screenshot keyboard-enter-text enter run-all!]]))

(define-uia-test
  "user should be able to log in"
  (fn []
    (tap [:textField {:marked "Name"}])
    (keyboard-enter-text "Karl.Krukow@gmail.com")
    (enter)
    (screenshot "Menu")
    (tap [:button {:marked "Second"}])
    (sleep 0.3)
    (screenshot "Map")
    ))



(run-all!)
