(ns voice-recognition-identity.routes.home
  (:require [voice-recognition-identity.layout :as layout]
            [voice-recognition-identity.db.core :as db]
            [compojure.core :refer [defroutes GET]]
            [ring.util.http-response :as response]
            [clojure.java.io :as io]))

(defn home-page []
  (layout/render
    "home.html" {:docs (-> "docs/docs.md" io/resource slurp)}))

(defn about-page []
  (layout/render "about.html"))

(defroutes home-routes
           (GET "/" [] (home-page))
           (GET "/about" [] (about-page)))

