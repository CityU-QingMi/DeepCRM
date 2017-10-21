    public JspReader(JspCompilationContext ctxt,
                     String fname,
                     String encoding,
                     InputStreamReader reader,
                     ErrorDispatcher err)
            throws JasperException, FileNotFoundException {

        this.context = ctxt;
        this.err = err;
        sourceFiles = new Vector();
        currFileId = 0;
        size = 0;
        singleFile = false;
        pushFile(fname, encoding, reader);
    }
