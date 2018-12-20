(ns muon.install
  (:require ["os" :as os]
            ["path" :as path]
            ["child_process" :as proc]))


;; (def pkg-resolve (js/require.resolve "mkdirp"))
(def pkg-resolve (js/require.resolve "muon-download"))

(defn bin-name [pkg]
  (let [os (.platform os)]
    (case os
      "darwin" (.join path pkg "/vendor/Brave.app/Contents/MacOS/Brave")
      "win32" (.join path pkg "/vendor/brave.exe")
      (.join path pkg "/vendor/brave"))))

(defn main []
  (let [argv js/process.argv
        pkg (.dirname path pkg-resolve)
        bin (bin-name pkg)
        app (.slice argv 2)]
    (.log js/console "App file: " app)
    (.spawn proc bin app)))

(main)
