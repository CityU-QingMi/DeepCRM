    private void readBytesUntilNextSpace() throws IOException {
        for (int i = 0; i < lengthBufferSize; i++) {
            final int b = inputStream.read();
            if (b < 0) {
                throw new EOFException("The stream has been closed or the end of stream has been reached");
            }
            final byte currentByte = (byte)(b & 0xff);
            if (currentByte == SPACE) {
                position = i;
                break;
            }
            lengthBuffer[i] = currentByte;
        }
    }
