    public Class loadTagFile() throws JasperException {

        try {
            if (ctxt.isRemoved()) {
                throw new FileNotFoundException(jspUri);
            }
            if (options.getDevelopment() || firstTime ) {
                synchronized (this) {
                    firstTime = false;
                    ctxt.compile();
                }
            } else {
                if (compileException != null) {
                    throw compileException;
                }
            }

            if (reload) {
                tagHandlerClass = ctxt.load();
                reload = false;
            }
        } catch (FileNotFoundException ex) {
            throw new JasperException(ex);
	}

	return tagHandlerClass;
    }
