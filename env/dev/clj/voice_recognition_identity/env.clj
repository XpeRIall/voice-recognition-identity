(ns voice-recognition-identity.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [voice-recognition-identity.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
               (fn []
                 (parser/cache-off!)
                 (log/info "\n-=[voice-recognition-identity started successfully using the development profile]=-"))
   :stop
               (fn []
                 (log/info "\n-=[voice-recognition-identity has shut down successfully]=-"))
   :middleware wrap-dev})
