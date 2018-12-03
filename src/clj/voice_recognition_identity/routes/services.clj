(ns voice-recognition-identity.routes.services
  (:require [ring.middleware.anti-forgery :refer :all]
            [ring.middleware.session :refer :all]
            [ring.util.http-response :refer :all]
            [compojure.api.sweet :refer :all]
            [schema.core :as s]
            [compojure.api.meta :refer [restructure-param]]
            [buddy.auth.accessrules :refer [restrict]]
            [buddy.auth :refer [authenticated?]]
            [voice-recognition-identity.db.core :as core]
            ))

(defn access-error [_ _]
  (unauthorized {:error "unauthorized"}))

(defn wrap-restricted [handler rule]
  (restrict handler {:handler  rule
                     :on-error access-error}))

(defmethod restructure-param :auth-rules
  [_ rule acc]
  (update-in acc [:middleware] conj [wrap-restricted rule]))

(defmethod restructure-param :current-user
  [_ binding acc]
  (update-in acc [:letks] into [binding `(:identity ~'+compojure-api-request+)]))

(defapi service-routes
  {:swagger {:ui   "/swagger"
             :spec "/swagger.json"
             :data {:info {:version     "1.0.0"
                           :title       "Voice recognition authentication service "
                           :description "Authentication Services"}}}}

  (POST "/login" req
    :return {:token String}
    :body-params [email :- String password :- String]
    :summary "User logged in"
    (let [token
          (core/signin email password)]
      (cond
        (some? token) (ok {:token token})
        :else (bad-request "Wrong Credentials"))))

  (POST "/signup" req
    :return {:token String}
    :body-params [first_name :- String last_name :- String email :- String password :- String]
    :summary "User signed up"
    (let [token (core/signup first_name last_name email password)]
      (cond
        (some? token) (ok {:token token})
        :else (bad-request "User is already registered")))))
