    public static Charset getCharsetLower(String lowerCaseEnc)
            throws UnsupportedEncodingException {

        Charset charset = encodingToCharsetCache.get(lowerCaseEnc);

        if (charset == null) {
            // Pre-population of the cache means this must be invalid
            throw new UnsupportedEncodingException("The character encoding " + lowerCaseEnc + " is not supported");
        }
        return charset;
    }
