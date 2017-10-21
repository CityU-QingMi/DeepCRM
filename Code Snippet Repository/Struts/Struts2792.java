    public JspReader(JspCompilationContext ctxt,
                     String fname,
                     String encoding,
                     JarFile jarFile,
                     ErrorDispatcher err)
            throws JasperException, FileNotFoundException, IOException {

        this(ctxt, fname, encoding,
             JspUtil.getReader(fname, encoding, jarFile, ctxt, err),
             err);
    }
