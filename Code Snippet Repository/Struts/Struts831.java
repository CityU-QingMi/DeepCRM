    public byte[] toByteArray() {
        byte data[] = new byte[getSize()];
        int position = 0;
        if (buffers != null) {
            for (byte[] bytes : buffers) {
                System.arraycopy(bytes, 0, data, position, blockSize);
                position += blockSize;
            }
        }
        System.arraycopy(buffer, 0, data, position, index);
        return data;
    }
