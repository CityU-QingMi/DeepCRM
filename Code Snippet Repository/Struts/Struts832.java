    public void write(byte data[], int offset, int length) throws IOException {
        if (data == null) {
            throw new NullPointerException();
        }
        if (offset < 0 || offset + length > data.length || length < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (closed) {
            throw new IOException("Stream closed");
        }
        if (index + length > blockSize) {
            do {
                if (index == blockSize) {
                    addBuffer();
                }
                int copyLength = blockSize - index;
                if (length < copyLength) {
                    copyLength = length;
                }
                System.arraycopy(data, offset, buffer, index, copyLength);
                offset += copyLength;
                index += copyLength;
                length -= copyLength;
            } while (length > 0);
        } else {
            System.arraycopy(data, offset, buffer, index, length);
            index += length;
        }
    }
