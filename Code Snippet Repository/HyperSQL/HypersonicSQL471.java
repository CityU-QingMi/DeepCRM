        public CompileContext(Session session, ParserBase parser,
                              CompileContext baseContext) {

            this.session     = session;
            this.parser      = parser;
            this.baseContext = baseContext;

            if (baseContext != null) {
                rangeVarIndex = baseContext.getRangeVarCount();
                subqueryDepth = baseContext.getDepth();
            }
        }
