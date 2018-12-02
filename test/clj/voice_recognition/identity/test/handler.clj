(ns voice-recognition.identity.test.handler
  (:require [clojure.test :refer :all]
            [ring.mock.request :refer :all]
            [voice-recognition.identity.handler :refer :all]
            [voice-recognition.identity.middleware.formats :as formats]
            [muuntaja.core :as m]
            [mount.core :as mount]))

(defn parse-json [body]
  (m/decode formats/instance "application/json" body))

(use-fixtures
  :once
  (fn [f]
    (mount/start #'voice-recognition.identity.config/env
                 #'voice-recognition.identity.handler/app)
    (f)))

(deftest test-app
  (testing "main route"
    (let [response (app (request :get "/"))]
      (is (= 200 (:status response)))))

  (testing "not-found route"
    (let [response (app (request :get "/invalid"))]
      (is (= 404 (:status response))))))
