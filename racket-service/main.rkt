#lang racket

(module+ main
  (require racket/cmdline)
  (require json)
  (require "drracket-api.rkt")

  (command-line
   #:program "racket-diganostic"
   #:args (file)
   (define j
     (map
      diganostic->json
      (digano-file file)))
   (displayln (jsexpr->string j))))
