    public long position(SessionInterface session, byte[] pattern,
                         long start) {

        if (pattern.length > data.length) {
            return -1;
        }

        if (start >= data.length) {
            return -1;
        }

        return ArrayUtil.find(data, (int) start, data.length, pattern);
    }
