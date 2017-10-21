    private static void decodeAndWriteBuffered(Writer writer, ByteBuffer in, CharBuffer out, CharsetDecoder decoder, boolean endOfInput) throws IOException {
        // Decode
        CoderResult result;
        do {
            result = decodeAndWrite(writer, in, out, decoder, endOfInput);
            // Check that all data are decoded
            if (in.hasRemaining()) {
                // Move remaining to top of buffer
                in.compact();
                if (result.isOverflow() && !result.isError() && !result.isMalformed()) {
                    // Not all buffer chars decoded, spin it again
                    // Set to begin
                    in.flip();
                }
            } else {
                // Clean up buffer
                in.clear();
            }
        } while (in.hasRemaining() && result.isOverflow() && !result.isError() && !result.isMalformed());
    }
