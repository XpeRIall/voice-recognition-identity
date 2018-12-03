(ns voice-recognition-identity.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[voice-recognition-identity started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[voice-recognition-identity has shut down successfully]=-"))
   :middleware identity})
