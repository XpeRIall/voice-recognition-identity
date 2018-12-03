(ns voice-recognition-identity.helpers.crypto
  (:require [buddy.sign.jwt :as jwt]
            [buddy.core.hash :as hash]
            [buddy.core.codecs :refer :all]))
(import 'java.security.MessageDigest
        'java.math.BigInteger)

(def secret "86bae26023208e57a5880d5ad644143c567fc57baaf5a942")

(defn generate-signature [email]
  (jwt/sign {:user email} secret))

(defn unsign-token [token]
  (jwt/unsign token secret))

(defn genMD5Hash [^String s]
  (->> s
       .getBytes
       (.digest (MessageDigest/getInstance "MD5"))
       (BigInteger. 1)
       (format "%032x")))

(defn verify-pass [hash pass]
  (cond
    (= (genMD5Hash pass) hash) hash
    :else nil))