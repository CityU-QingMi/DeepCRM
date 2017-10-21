    private byte[] getBytes(String s) {

        byte[] bytes = null;

        try {
            bytes = s.getBytes(textFileSettings.charEncoding);
        } catch (UnsupportedEncodingException e) {
            throw Error.error(ErrorCode.TEXT_FILE_IO, e);
        }

        return bytes;
    }
