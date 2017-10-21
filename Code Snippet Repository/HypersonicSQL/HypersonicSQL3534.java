    public byte[] getBytes(SessionInterface session, long pos, int length) {

        if (!isInLimits(data.length, pos, length)) {
            throw new IndexOutOfBoundsException();
        }

        byte[] bytes = new byte[length];

        System.arraycopy(data, (int) pos, bytes, 0, length);

        return bytes;
    }
