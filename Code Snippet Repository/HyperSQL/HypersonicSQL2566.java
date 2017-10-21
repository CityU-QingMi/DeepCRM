    private void inflate(byte[] data, int length, boolean isClob) {

        if (cryptLobs) {
            length = database.logger.getCrypto().decode(data, 0, length, data,
                    0);
        }

        try {
            inflater.setInput(data, 0, length);

            length = inflater.inflate(dataBuffer);

            inflater.reset();
        } catch (DataFormatException e) {

            //
        } catch (Throwable e) {}

        int limit = (int) ArrayUtil.getBinaryMultipleCeiling(length,
            lobBlockSize);

        for (int i = length; i < limit; i++) {
            dataBuffer[i] = 0;
        }
    }
