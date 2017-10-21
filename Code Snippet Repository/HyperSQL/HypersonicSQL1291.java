    private void setEncoding(String newEncoding)
            throws UnsupportedEncodingException {
        if (newEncoding == null || newEncoding.length() < 1) {
            shared.encoding = null;
            shared.userVars.remove("*ENCODING");
            return;
        }
        if (!Charset.isSupported(newEncoding))
            throw new UnsupportedEncodingException(newEncoding);
        shared.userVars.put("*ENCODING", newEncoding);
        shared.encoding = newEncoding;
    }
