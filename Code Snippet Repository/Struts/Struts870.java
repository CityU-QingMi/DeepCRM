    public B2CConverter(String encoding, boolean replaceOnError)
            throws IOException {
        byte[] left = new byte[LEFTOVER_SIZE];
        leftovers = ByteBuffer.wrap(left);
        CodingErrorAction action;
        if (replaceOnError) {
            action = CodingErrorAction.REPLACE;
        } else {
            action = CodingErrorAction.REPORT;
        }
        Charset charset = getCharset(encoding);
        // Special case. Use the Apache Harmony based UTF-8 decoder because it
        // - a) rejects invalid sequences that the JVM decoder does not
        // - b) fails faster for some invalid sequences
        if (charset.equals(UTF_8)) {
            decoder = new Utf8Decoder();
        } else {
            decoder = charset.newDecoder();
        }
        decoder.onMalformedInput(action);
        decoder.onUnmappableCharacter(action);
    }
