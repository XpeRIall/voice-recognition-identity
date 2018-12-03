(ns voice-recognition-identity.test.db.core
  (:require
    [voice-recognition-identity.db.core :as db]
    [luminus-migrations.core :as migrations]
    [clojure.test :refer :all]
    [clojure.java.jdbc :as jdbc]
    [voice-recognition-identity.config :refer [env]]
    [mount.core :as mount]))

(use-fixtures
  :once
  (fn [f]
    (mount/start
      #'voice-recognition-identity.config/env
      #'voice-recognition-identity.db.core/pg)
    (migrations/migrate ["migrate"] (select-keys env [:database-url]))
    (f)))