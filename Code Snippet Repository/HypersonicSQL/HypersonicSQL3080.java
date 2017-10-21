    public ScriptWriterEncode(Database db, OutputStream outputStream,
                              FileAccess.FileSync descriptor,
                              boolean includeCached, Crypto crypto) {

        super(db, outputStream, descriptor, includeCached);

        try {
            cryptOut      = crypto.getOutputStream(fileStreamOut);
            fileStreamOut = new GZIPOutputStream(cryptOut);
            isCrypt       = true;
        } catch (IOException e) {
            throw Error.error(e, ErrorCode.FILE_IO_ERROR,
                              ErrorCode.M_Message_Pair, new Object[] {
                e.toString(), outFile
            });
        }
    }
