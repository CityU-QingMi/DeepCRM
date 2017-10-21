    @Deprecated
    public int substract(ByteChunk src)
            throws IOException {

        if ((end - start) == 0) {
            if (in == null) {
                return -1;
            }
            int n = in.realReadBytes( buff, 0, buff.length );
            if (n < 0) {
                return -1;
            }
        }

        int len = getLength();
        src.append(buff, start, len);
        start = end;
        return len;

    }
