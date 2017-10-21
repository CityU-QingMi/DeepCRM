    public void writeTo(Writer out, String encoding) throws IOException {
        if (encoding != null) {
            CharsetDecoder decoder = getDecoder(encoding);
            // Create buffer for characters decoding
            CharBuffer charBuffer = CharBuffer.allocate(buffer.length);
            // Create buffer for bytes
            float bytesPerChar = decoder.charset().newEncoder().maxBytesPerChar();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) (buffer.length + bytesPerChar));
            if (buffers != null) {
                for (byte[] bytes : buffers) {
                    decodeAndWriteOut(out, bytes, bytes.length, byteBuffer, charBuffer, decoder, false);
                }
            }
            decodeAndWriteOut(out, buffer, index, byteBuffer, charBuffer, decoder, true);
        } else {
            if (buffers != null) {
                for (byte[] bytes : buffers) {
                    writeOut(out, bytes, bytes.length);
                }
            }
            writeOut(out, buffer, index);
        }
    }
