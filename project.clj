(defproject reagent-title-demo "0.1.0-SNAPSHOT"
  :description "Reagent head>title demo"
  :url "http://ducky427.github.io/reagent-title-demo"

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.228"]
                 [reagent "0.5.1"]]

  :node-dependencies [[source-map-support "0.2.8"]]

  :plugins [[lein-cljsbuild "1.1.2"]]

  :source-paths ["src"]

  :clean-targets ^{:protect false} [:target-path
                                    [:cljsbuild :builds :app :compiler :output-dir]
                                    [:cljsbuild :builds :app :compiler :output-to]]

  :cljsbuild {:builds {:app {:source-paths ["src"]
                             :compiler {:output-to     "resources/public/js/compiled/app.js"
                                        :optimizations :none
                                        :pretty-print   true
                                        :cache-analysis true
                                        :language-out :ecmascript5}}}}

  :figwheel {:css-dirs ["resources/public/css"]}

  :profiles {:dev    {:cljsbuild {:builds {:app {:source-paths ["env/dev"]
                                                 :compiler {:main "reagent-title-demo.dev"
                                                            :source-map true
                                                            :output-dir "resources/public/js/compiled/out"
                                                            :asset-path "js/compiled/out"
                                                            :source-map-timestamp true}
                                                 :figwheel {:on-jsload "reagent-title-demo.core/mount-root"}}}}
                      :plugins [[lein-ancient "0.6.8"]
                                [lein-kibit "0.1.2"]
                                [lein-cljfmt "0.3.0"]
                                [lein-figwheel "0.5.0-3"]]}

             :production {:cljsbuild {:builds {:app {:source-paths ["env/prod"]
                                                     :compiler {:main "reagent-title-demo.prod"
                                                                :optimizations :advanced
                                                                :pretty-print false
                                                                :output-dir "target/out"
                                                                :closure-defines {goog.DEBUG false}}}}}}}

  :aliases {"build" ["do" "clean" ["with-profile" "production" "cljsbuild" "once"]]})
