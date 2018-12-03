(ns voice-recognition-identity.db.core
  (:use [korma.core]
        [korma.db])
  (:require [voice-recognition-identity.helpers.crypto :as crypto]))
(defdb pg (postgres
            {
             :host       "localhost"
             :port       "5432"
             :db         "voice_recognition_identity_dev"
             :user       "postgres"
             :password   "postgres"
             :delimiters ""
             }))
(defentity users
           (entity-fields :id :first_name :last_name :email :pass))


(defn getUsers [] (select users))

(defn signin [email password]
  (let [user (first (select users (where {:email email})))]
    (println (user :pass))
    (cond (some? (crypto/verify-pass (user :pass) password)) (
                                                               (println (crypto/generate-signature email))
                                                               (crypto/generate-signature email))
          :else nil)))

(defn signup [first_name last_name email password]
  (let [user (select users (where {:email email}))]
    (cond
      (empty? user) (
                      (insert users
                              (values
                                {
                                 :first_name first_name
                                 :last_name  last_name
                                 :email      email :pass (crypto/genMD5Hash password)
                                 }))
                      (println (crypto/generate-signature email))
                      (crypto/generate-signature email))
      :else nil)
    )
  )

