    public long position(SessionInterface session, BlobData pattern,
                         long start) {

        if (pattern.length(session) > data.length) {
            return -1;
        }

        byte[] bytes = pattern.getBytes(session, 0,
                                        (int) pattern.length(session));

        return position(session, bytes, start);
    }
