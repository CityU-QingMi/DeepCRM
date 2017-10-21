    private Object[] getEncoding(InputStream in, ErrorDispatcher err)
            throws IOException, JasperException {
        this.stream = in;
        this.err = err;
        createInitialReader();
        scanXMLDecl();

        return new Object[]{this.encoding,
                Boolean.valueOf(this.isEncodingSetInProlog),
                Boolean.valueOf(this.isBomPresent),
                Integer.valueOf(this.skip)};
    }
