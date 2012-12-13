# calabash-script-example

Provides a sample Calabash-Script project using the 2012 Olympics app:

[https://github.com/Frahaan/2012-Olympics-iOS--iPad-and-iPhone--source-code](https://github.com/Frahaan/2012-Olympics-iOS--iPad-and-iPhone--source-code)

## Follow along

Checkout

    git clone https://github.com/Frahaan/2012-Olympics-iOS--iPad-and-iPhone--source-code.git


Build

    cd 2012-Olympics-iOS--iPad-and-iPhone--source-code/2012\ Olympics
    xcodebuild build -target "2012 Olympics" -configuration Release -sdk iphonesimulator5.1 DEPLOYMENT_LOCATION=YES DSTROOT=build TARGETED_DEVICE_FAMILY=1


(This requires XCode 4.5 with command line tools installed).

Checkout sample




    mkdir run





## License

Copyright © 2012 Karl Krukow

Distributed under the Eclipse Public License, the same as Clojure.
