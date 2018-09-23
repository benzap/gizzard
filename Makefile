# Makefile for Gizzard
.PHONY: test test-clj test-cljs
.PHONY: help version


help:
	@echo "Gizzard Makefile"
	@echo ""
	@echo "  Commands:"
	@echo ""
	@echo "    test       Perform all tests."
	@echo "    test-clj   Perform tests in clojure."
	@echo "    test-cljs  Perform tests in clojurescript."
	@echo ""
	@echo "    version    Get Project Version"


test: test-clj test-cljs


test-clj:
	lein test


test-cljs:
	lein doo once


version:
	lein project-version


