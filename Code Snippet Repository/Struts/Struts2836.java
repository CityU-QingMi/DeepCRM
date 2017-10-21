    static InputStreamReader getReader(String fname, String encoding,
                                       JarFile jarFile,
                                       JspCompilationContext ctxt,
                                       ErrorDispatcher err, int skip)
            throws JasperException, IOException {

        InputStreamReader reader = null;
        InputStream in = getInputStream(fname, jarFile, ctxt, err);
        for (int i = 0; i < skip; i++) {
            in.read();
        }
        try {
            reader = new InputStreamReader(in, encoding);
        } catch (UnsupportedEncodingException ex) {
            err.jspError("jsp.error.unsupported.encoding", encoding);
        }

        return reader;
    }
