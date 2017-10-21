    public ScriptWriterEncode(Database db, String file, boolean includeCached,
                              Crypto crypto) {

        super(db, file, includeCached, true, false);

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
