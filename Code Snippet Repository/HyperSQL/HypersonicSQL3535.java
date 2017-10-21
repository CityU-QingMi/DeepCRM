    public void setBytes(SessionInterface session, long pos, byte[] bytes,
                         int offset, int length) {

        if (!isInLimits(data.length, pos, 0)) {
            throw new IndexOutOfBoundsException();
        }

        if (!isInLimits(data.length, pos, length)) {
            data = (byte[]) ArrayUtil.resizeArray(data, (int) pos + length);
        }

        System.arraycopy(bytes, offset, data, (int) pos, length);

        bitLength = data.length * 8L;
    }
