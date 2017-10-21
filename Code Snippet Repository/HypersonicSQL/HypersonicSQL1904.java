    public synchronized void setSize(int newSize) {

        if (newSize < 0) {
            throw new ArrayIndexOutOfBoundsException(newSize);
        } else if (newSize > buf.length) {
            buf = copyOf(buf, Math.max(buf.length << 1, newSize));
        }

        count = newSize;
    }
