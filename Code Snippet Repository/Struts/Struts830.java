    private static CoderResult decodeAndWrite(Writer writer, ByteBuffer in, CharBuffer out, CharsetDecoder decoder, boolean endOfInput) throws IOException {
        CoderResult result = decoder.decode(in, out, endOfInput);
        // To begin of decoded data
        out.flip();
        // Output
        writer.write(out.toString());
        // clear output to avoid infinitive loops, see WW-4383
        out.clear();
        return result;
    }
