    public Object concat(Session session, Object a, Object b) {

        if (a == null || b == null) {
            return null;
        }

        long length = ((BlobData) a).length(session)
                      + ((BlobData) b).length(session);

        if (length > precision) {
            throw Error.error(ErrorCode.X_22001);
        }

        return new BinaryData(session, (BlobData) a, (BlobData) b);
    }
