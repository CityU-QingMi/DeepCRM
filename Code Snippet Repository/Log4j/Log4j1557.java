    public static byte[] toBytes(final String str, final Charset charset) {
        if (str != null) {
            if (StandardCharsets.ISO_8859_1.equals(charset)) {
                return encodeSingleByteChars(str);
            }
            final Charset actual = charset != null ? charset : Charset.defaultCharset();
            try { // LOG4J2-935: String.getBytes(String) gives better performance
                return str.getBytes(actual.name());
            } catch (final UnsupportedEncodingException e) {
                return str.getBytes(actual);
            }
        }
        return null;
    }
