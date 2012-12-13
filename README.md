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

    git clone https://github.com/krukow/calabash-script.git
    cd calabash-script/
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
	
	user=> (def proc (run-tests))

	[instruments -t /Applications/Xcode.app/Contents/Applications/Instruments.app/Contents/PlugIns/AutomationInstrument.bundle/Contents/Resources/Automation.tracetemplate /Users/krukow/github/2012-Olympics-iOS--iPad-and-iPhone--source-code/2012 Olympics/build/Applications/2012 Olympics.app -D run/trace -e UIARESULTSPATH run -e UIASCRIPT build/test_script.js]	 

	user=> (slurp (:input proc))

	"2012-12-13 19:23:14 +0000 Default: Running tests: (\"Archery details should be accessible via Events\")\n2012-12-13 19:23:14 +0000 Start: Archery details should be accessible via Events\n2012-12-13 19:23:14 +0000 Debug: (null).tap()\n2012-12-13 19:23:14 +0000 Debug: target.captureRectWithName(\"{origin:{x:0.00,y:0.00}, size:{height:480.00,width:320.00}}\", \"Events1\")\n2012-12-13 19:23:14 +0000 Screenshot captured.\n2012-12-13 19:23:17 +0000 Debug: (null).scrollToVisible()\n2012-12-13 19:23:25 +0000 Debug: target.captureRectWithName(\"{origin:{x:0.00,y:0.00}, size:{height:480.00,width:320.00}}\", \"Events2\")\n2012-12-13 19:23:25 +0000 Screenshot captured.\n2012-12-13 19:23:25 +0000 Debug: (null).tap()\n2012-12-13 19:23:27 +0000 Debug: target.captureRectWithName(\"{origin:{x:0.00,y:0.00}, size:{height:480.00,width:320.00}}\", \"Water polo details3\")\n2012-12-13 19:23:27 +0000 Screenshot captured.\n2012-12-13 19:23:27 +0000 Pass: Archery details should be accessible via Events\n2012-12-13 19:23:27 +0000 Default: {\"Archery details should be accessible via Events\" {:result :pass}}\nInstruments Trace Complete (Duration : 17.275938s; Output : /Users/krukow/github/2012-Olympics-iOS--iPad-and-iPhone--source-code/2012 Olympics/calabash-script/instrumentscli3.trace)\n"



## License

Copyright © 2012 Karl Krukow

Distributed under the Eclipse Public License, the same as Clojure.
