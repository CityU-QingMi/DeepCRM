    void setSpaceAndLineEnd() {

        try {
            if (isUTF16) {
                bytesForLineEnd = NL.getBytes(charEncoding);
                bytesForSpace   = " ".getBytes(charEncoding);
            }
        } catch (UnsupportedEncodingException e) {
            throw Error.error(ErrorCode.X_S0531);
        }
    }
