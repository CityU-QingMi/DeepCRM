    public ScriptReaderDecode(Database db, String fileName, Crypto crypto,
                              boolean forLog) throws IOException {

        super(db, fileName);

        this.crypto = crypto;

        try {
            inputStream =
                db.logger.getFileAccess().openInputStreamElement(fileName);
            bufferedStream = new BufferedInputStream(inputStream);
            rowIn          = new RowInputTextLog();

            if (forLog) {
                dataInput = new DataInputStream(bufferedStream);
            } else {
                cryptoStream = crypto.getInputStream(bufferedStream);
                gzipStream   = new GZIPInputStream(cryptoStream);
                dataStreamIn = new LineReader(gzipStream,
                                              ScriptWriterText.ISO_8859_1);
            }
        } catch (Throwable t) {
            close();

            throw JavaSystem.toIOException(t);
        }
    }
