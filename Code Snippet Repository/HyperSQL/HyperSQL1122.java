    public void writeInt(int v) {

        if (count + 4 > buffer.length) {
            ensureRoom(4);
        }

        buffer[count++] = (byte) (v >>> 24);
        buffer[count++] = (byte) (v >>> 16);
        buffer[count++] = (byte) (v >>> 8);
        buffer[count++] = (byte) v;
    }
