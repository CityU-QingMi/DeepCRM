    protected OutputStream setBinaryStreamImpl() throws SQLException {

        this.outputStream = new ClosableByteArrayOutputStream();

        try {
            return new GZIPOutputStream(this.outputStream);
        } catch (IOException ex) {
            this.outputStream = null;

            throw Exceptions.resultInstantiation(ex);
        }
    }
