    public ScriptReaderText(Database db, String fileName,
                            boolean compressed) throws IOException {

        super(db, fileName);

        inputStream =
            database.logger.getFileAccess().openInputStreamElement(fileName);
        bufferedStream = new BufferedInputStream(inputStream);

        InputStream tempStream;

        if (compressed) {
            gzipStream = new GZIPInputStream(bufferedStream);
            tempStream = gzipStream;
        } else {
            tempStream = bufferedStream;
        }

        dataStreamIn = new LineReader(tempStream, ScriptWriterText.ISO_8859_1);
        rowIn = new RowInputTextLog(db.databaseProperties.isVersion18());
    }
