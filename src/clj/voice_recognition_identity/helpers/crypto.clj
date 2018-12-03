(ns voice-recognition-identity.helpers.crypto
  (:require [buddy.sign.jwt :as jwt]
            [buddy.core.codecs :refer :all]))
(import 'java.security.MessageDigest
        'java.math.BigInteger)

(def secret "0SgucHt!`-B_^aUG-:}5T3JE1{O-6-TQ?-_I7KPW?guZ=Ks`@miVAn3djDx~s=`")

(defn generate-signature [email]
  (jwt/sign {:user email} secret))

(defn unsign-token [token]
  (jwt/unsign token secret))

(defn genMD5Hash [^String s]
  (->>
    s
    .getBytes
    (.digest (MessageDigest/getInstance "MD5"))
    (BigInteger. 1)
    (format "%032x")))

(defn verify-pass [pass hash]
  (let [pass_hash (genMD5Hash pass)]
    (= pass_hash hash)))