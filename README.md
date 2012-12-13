# calabash-script-example

Provides a sample Calabash-Script project using the 2012 Olympics app:

[https://github.com/Frahaan/2012-Olympics-iOS--iPad-and-iPhone--source-code](https://github.com/Frahaan/2012-Olympics-iOS--iPad-and-iPhone--source-code)

## Running the first test
Checkout

    git clone https://github.com/Frahaan/2012-Olympics-iOS--iPad-and-iPhone--source-code.git


Build

    cd 2012-Olympics-iOS--iPad-and-iPhone--source-code/2012\ Olympics
    xcodebuild build -target "2012 Olympics" -configuration Release -sdk iphonesimulator5.1 DEPLOYMENT_LOCATION=YES DSTROOT=build TARGETED_DEVICE_FAMILY=1

(This requires XCode 4.5 with command line tools installed).

Save the location of the built app
	
	export APP="`pwd`/build/Applications/2012 Olympics.app"

Checkout and compile sample

    git clone https://github.com/krukow/calabash-script-example.git
    cd calabash-script-example/
    mkdir run    
    lein deps
    lein cljsbuild once

Take a look at the sample test

	cat src/calabash_script_example/core.cljs 

```clojure
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
```

Run the first test to verify it works:

	lein repl
	user=> (use 'calabash-script-example.repl)
	nil
	
	user=> (System/getenv "APP")	
	"/Users/krukow/github/2012-Olympics-iOS--iPad-and-iPhone--source-code/2012 Olympics/build/Applications/2012 Olympics.app"
	
	user=> (def proc (run-tests)) ;notice simulator may be behind windows

	[instruments -t /Applications/Xcode.app/Contents/Applications/Instruments.app/Contents/PlugIns/AutomationInstrument.bundle/Contents/Resources/Automation.tracetemplate /Users/krukow/github/2012-Olympics-iOS--iPad-and-iPhone--source-code/2012 Olympics/build/Applications/2012 Olympics.app -D run/trace -e UIARESULTSPATH run -e UIASCRIPT build/test_script.js]	 

	user=> (slurp (:input proc))

	"2012-12-13 19:23:14 +0000 Default: Running tests: (\"Archery details should be accessible via Events\")\n2012-12-13 19:23:14 +0000 Start: Archery details should be accessible via Events\n2012-12-13 19:23:14 +0000 Debug: (null).tap()\n2012-12-13 19:23:14 +0000 Debug: target.captureRectWithName(\"{origin:{x:0.00,y:0.00}, size:{height:480.00,width:320.00}}\", \"Events1\")\n2012-12-13 19:23:14 +0000 Screenshot captured.\n2012-12-13 19:23:17 +0000 Debug: (null).scrollToVisible()\n2012-12-13 19:23:25 +0000 Debug: target.captureRectWithName(\"{origin:{x:0.00,y:0.00}, size:{height:480.00,width:320.00}}\", \"Events2\")\n2012-12-13 19:23:25 +0000 Screenshot captured.\n2012-12-13 19:23:25 +0000 Debug: (null).tap()\n2012-12-13 19:23:27 +0000 Debug: target.captureRectWithName(\"{origin:{x:0.00,y:0.00}, size:{height:480.00,width:320.00}}\", \"Water polo details3\")\n2012-12-13 19:23:27 +0000 Screenshot captured.\n2012-12-13 19:23:27 +0000 Pass: Archery details should be accessible via Events\n2012-12-13 19:23:27 +0000 Default: {\"Archery details should be accessible via Events\" {:result :pass}}\nInstruments Trace Complete (Duration : 17.275938s; Output : /Users/krukow/github/2012-Olympics-iOS--iPad-and-iPhone--source-code/2012 Olympics/calabash-script/instrumentscli3.trace)\n"

## UIA has a ClojureScript REPL

We can have a ClojureScript REPL for interacting with UIAutomation! This is running quite slowly, but is still much better than the traditional development experience with UIAutomation.  Make sure you watch the simulator while you run the REPL.

	lein repl
	user=> (use 'calabash-script-example.repl)
	nil

```clojure
user=> (start-repl)
[instruments -t /Applications/Xcode.app/Contents/Applications/Instruments.app/Contents/PlugIns/AutomationInstrument.bundle/Contents/Resources/Automation.tracetemplate /Users/krukow/github/2012-Olympics-iOS--iPad-and-iPhone--source-code/2012 Olympics/build/Applications/2012 Olympics.app -D run/trace -e UIARESULTSPATH run -e UIASCRIPT /var/folders/63/lq05__ys7xjfcw7fn6vpz6fc0000gn/T/uia-repl8994143582132692320.js]
"Type: " :cljs/quit " to quit"
{:status :success :value nil}
MESSAGE: 2012-12-13 19:47:21 +0000 Default: 
{:status :success :value {:cljs$lang$maxFixedArity 0 :cljs$lang$applyTo {} :cljs$lang$arity$variadic {}}}
ClojureScript:cljs.user>   #_=> (+ 1 2 3)
MESSAGE: 2012-12-13 19:47:23 +0000 Default: 
{:status :success :value "6"}
6
ClojureScript:cljs.user>   #_=> (ns example (:require [calabash-script.core :as c]))
"load " #<URL jar:file:/Users/krukow/.m2/repository/org/clojure/google-closure-library/0.0-2029/google-closure-library-0.0-2029.jar!/goog/string/string.js>
…
ClojureScript:example>   #_=> (c/query [:tabBar :button])
MESSAGE: 2012-12-13 19:54:00 +0000 Default: 
...
({:name "Home", :rect {:x 2, :y 432, :height 48, :width 60}, :hit-point {:x 32, :y 456}, :el #<[object UIAButton]>, :label "Home"} {:name "Events", :rect {:x 66, :y 432, :height 48, :width 60}, :hit-point {:x 96, :y 456}, :el #<[object UIAButton]>, :label "Events"} {:name "Count Down", :rect {:x 130, :y 432, :height 48, :width 60}, :hit-point {:x 160, :y 456}, :el #<[object UIAButton]>, :label "Count Down"} {:name "Schedule", :rect {:x 194, :y 432, :height 48, :width 60}, :hit-point {:x 224, :y 456}, :el #<[object UIAButton]>, :label "Schedule"} {:name "Anthem", :rect {:x 258, :y 432, :height 48, :width 60}, :hit-point {:x 288, :y 456}, :el #<[object UIAButton]>, :label "Anthem"})

ClojureScript:example>      #_=> (c/names [:tabBar :button])
MESSAGE: 2012-12-13 19:55:52 +0000 Default: 
{:status :success :value "(\"Home\" \"Events\" \"Count Down\" \"Schedule\" \"Anthem\")"}
("Home" "Events" "Count Down" "Schedule" "Anthem")

ClojureScript:example>      #_=> (c/tap [:view {:marked "Events"}])
MESSAGE: 2012-12-13 19:56:29 +0000 Default: 
MESSAGE: 2012-12-13 19:56:37 +0000 Debug: (null).tap()
{:status :success :value ""}

```	
	

## License

Copyright © 2012 Karl Krukow

Distributed under the Eclipse Public License, the same as Clojure.
