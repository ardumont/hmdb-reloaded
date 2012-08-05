(ns test-lily.core
  (:use [midje.sweet])
  (:import [org.lilyproject.client         LilyClient]
           [org.lilyproject.repository.api Scope QName]
           [org.lilyproject.util.repo      PrintUtil]))

;; In the vm
;; start the lily server
;; ~/start-lily.sh

;; ############### Setup

;; Init the connection to the lily server
(def lily-client (LilyClient. "localhost:2181" 20000))
(def repository (.. lily-client getRepository))
(def type-manager (.. repository getTypeManager))

;; ############### Create some field types

;; we will work in the namespace 'namespace-test'

(def string-value-type (.. type-manager (getValueType "STRING")))

;; create some field type name for the data we persist
(def qname-title (QName. "namespace-test" "title"))

;; create the field type bean (no persisting yet)
(def field-type-title (.. type-manager (newFieldType string-value-type qname-title Scope/VERSIONED)))

;; persist the bean field-type-title
(.. type-manager (createFieldType field-type-title) )

;; list the field types persisted
(.. type-manager getFieldTypes)

;; oops
;; i forgot to keep the reference the first time
;; so i update it but this time i kept the reference!
( def title (.. type-manager (createOrUpdateFieldType field-type-title)))

;; ############### Now create some record types

;; ############### Now Create records

;; ############### Read records

;; ############### Update records

;; ############### Delete records



