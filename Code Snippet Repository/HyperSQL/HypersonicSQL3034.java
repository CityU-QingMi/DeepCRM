    public void writeBytes(String s) {

        if (textFileSettings.isUTF16) {
            try {
                if (s.length() > 0) {
                    byte[] bytes = s.getBytes(textFileSettings.charEncoding);

                    super.write(bytes);
                }
            } catch (UnsupportedEncodingException e) {
                throw Error.error(ErrorCode.TEXT_FILE_IO, e);
            }
        } else {
            super.writeBytes(s);
        }
    }
