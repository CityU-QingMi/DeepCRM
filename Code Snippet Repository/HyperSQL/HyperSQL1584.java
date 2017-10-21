    protected void initBuffers() {

        if (rowOut == null) {
            if (is180) {
                rowOut = new RowOutputBinary180(initIOBufferSize,
                                                cachedRowPadding);
            } else {
                rowOut = new RowOutputBinaryEncode(database.logger.getCrypto(),
                                                   initIOBufferSize,
                                                   cachedRowPadding);
            }
        }

        if (rowIn == null) {
            if (is180) {
                rowIn = new RowInputBinary180(new byte[initIOBufferSize]);
            } else {
                rowIn = new RowInputBinaryDecode(database.logger.getCrypto(),
                                                 new byte[initIOBufferSize]);
            }
        }
    }
