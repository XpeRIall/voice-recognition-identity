(ns voice-recognition-identity.db.core
  (:use [korma.core]
        [korma.db])
  (:require [voice-recognition-identity.helpers.crypto :as crypto]))
(defdb pg (postgres {:host       "ec2-54-246-117-62.eu-west-1.compute.amazonaws.com"
                     :port       "5432"
                     :db         "d5h0o1e4c1rrr2"
                     :user       "xdhuwabztnlyoy"
                     :password   "5f716a497e23bf320de2a9c25d5a4f6efa3e1200466f3c38a9c2a03e608efde1"
                     :delimiters ""}))

(defentity users (entity-fields :id :first_name :last_name :email :pass))

(defn signin [email password]
  (let [user (first (select users (where {:email email})))]
    (let [hash (user :pass)]
      (cond
        (true? (crypto/verify-pass password hash)) (crypto/generate-signature email)
        :else nil))))

(defn signup [first_name last_name email password]
  (let [user (select users (where {:email email}))]
    (cond
      (empty? user) ((insert users (values
                                     {:first_name first_name
                                      :last_name  last_name
                                      :email      email :pass (crypto/genMD5Hash password)}))
                      (println (crypto/generate-signature email))
                      (crypto/generate-signature email))
      :else nil)
    )
  )

