    protected byte[] getBytes(final String s) {
        if (useCustomEncoding) { // rely on branch prediction to eliminate this check if false
            return StringEncoder.encodeSingleByteChars(s);
        }
        try { // LOG4J2-935: String.getBytes(String) gives better performance
            return s.getBytes(charsetName);
        } catch (final UnsupportedEncodingException e) {
            return s.getBytes(charset);
        }
    }
