#lang typed/racket

(require reporter)

(struct Foo ([name : String]) #:transparent)
(struct Bar ())

(: foo : Foo -> Foo)
(define (foo f)
  (Foo ""))
