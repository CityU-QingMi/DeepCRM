    public static Object[] getEncoding(String fname, JarFile jarFile,
                                       JspCompilationContext ctxt,
                                       ErrorDispatcher err)
            throws IOException, JasperException {
        InputStream inStream = JspUtil.getInputStream(fname, jarFile, ctxt,
                err);
        XMLEncodingDetector detector = new XMLEncodingDetector();
        Object[] ret = detector.getEncoding(inStream, err);
        inStream.close();

        return ret;
    }
