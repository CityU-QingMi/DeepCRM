    String digest(String string) throws RuntimeException {

        byte[] data;

        try {
            data = string.getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            throw Error.error(ErrorCode.GENERAL_ERROR, e);
        }

        data = getDigester().digest(data);

        return StringConverter.byteArrayToHexString(data);
    }
