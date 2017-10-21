    public ScriptWriterText(Database db, String file,
                            boolean includeCachedData, boolean compressed) {

        super(db, file, includeCachedData, true, false);

        if (compressed) {
            isCompressed = true;

            try {
                fileStreamOut = new GZIPOutputStream(fileStreamOut);
            } catch (IOException e) {
                throw Error.error(e, ErrorCode.FILE_IO_ERROR,
                                  ErrorCode.M_Message_Pair, new Object[] {
                    e.toString(), outFile
                });
            }
        }
    }
