    protected ErrorListener buildErrorListener() {
        return new ErrorListener() {

            public void error(TransformerException exception) throws TransformerException {
                throw new StrutsException("Error transforming result", exception);
            }

            public void fatalError(TransformerException exception) throws TransformerException {
                throw new StrutsException("Fatal error transforming result", exception);
            }

            public void warning(TransformerException exception) throws TransformerException {
                LOG.warn(exception.getMessage(), exception);
            }

        };
    }
