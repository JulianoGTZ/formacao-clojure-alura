(defproject stock "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}

  :plugins [[lein-cloverage "1.1.3-SNAPSHOT"]
            [lein-vanity "0.2.0"]
            [lein-nsorg "0.3.0"]
            [lein-cljfmt "0.6.4"]
            [jonase/eastwood "0.3.5"]
            [lein-kibit "0.1.6"]
            [s3-wagon-private "1.3.1"]
            [lein-ancient "0.6.15"]]

  :cljfmt {:indents {flow               [[:block 1]]
                     facts              [[:block 1]]
                     fact               [[:block 1]]
                     as-customer        [[:block 1]]
                     as-delegate        [[:block 2]]
                     as-of              [[:block 1]]
                     assoc-if           [[:block 1]]
                     let-entities       [[:block 2]]
                     provided           [[:inner 0]]
                     tabular            [[:inner 0]]
                     consume!           [[:block 0]]
                     try-type           [[:block 0]]
                     with-fn-validation [[:block 0]]
                     system-map         [[:block 0]]
                     request-context    [[:block 1]]}}

  :dependencies [[org.clojure/clojure "1.10.0"]
                 [prismatic/schema "1.1.12"]]

  :aliases {"lint"            ["do" ["cljfmt" "check"] ["nsorg"]]
            "lint-fix"        ["do" ["cljfmt" "fix"] ["nsorg" "--replace"]]}
  
  :repl-options {:init-ns stock.core})
