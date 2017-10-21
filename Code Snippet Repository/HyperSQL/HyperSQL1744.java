    private int deflate(byte[] data, int offset, int length, boolean isClob) {

        deflater.setInput(data, offset, length);
        deflater.finish();

        length = deflater.deflate(dataBuffer);

        deflater.reset();

        if (cryptLobs) {
            length = database.logger.getCrypto().encode(dataBuffer, 0, length,
                    dataBuffer, 0);
        }

        int limit = (int) ArrayUtil.getBinaryMultipleCeiling(length,
            lobBlockSize);

        for (int i = length; i < limit; i++) {
            dataBuffer[i] = 0;
        }

        return length;
    }
