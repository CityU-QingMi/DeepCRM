    public BinaryData(SessionInterface session, BlobData b1, BlobData b2) {

        long length = (b1.length(session) + b2.length(session));

        if (length > Integer.MAX_VALUE
                || b1.length(session) > Integer.MAX_VALUE
                || b2.length(session) > Integer.MAX_VALUE) {
            throw Error.error(ErrorCode.X_22001);
        }

        data = new byte[(int) length];

        System.arraycopy(b1.getBytes(session, 0, (int) b1.length(session)), 0,
                         data, 0, (int) b1.length(session));
        System.arraycopy(b2.getBytes(session, 0, (int) b2.length(session)), 0,
                         data, (int) b1.length(session),
                         (int) b2.length(session));

        this.bitLength = (int) length * 8L;
    }
