    public Object concat(Session session, Object a, Object b) {

        if (a == null || b == null) {
            return null;
        }

        long length = ((BlobData) a).length(session)
                      + ((BlobData) b).length(session);

        if (length > precision) {
            throw Error.error(ErrorCode.X_22001);
        }

        if (typeCode == Types.SQL_BLOB) {
            BlobData blob = session.createBlob(length);

            blob.setBytes(session, 0, ((BlobData) a), 0,
                          ((BlobData) a).length(session));
            blob.setBytes(session, ((BlobData) a).length(session),
                          ((BlobData) b), 0, ((BlobData) b).length(session));

            return blob;
        } else {
            return new BinaryData(session, (BlobData) a, (BlobData) b);
        }
    }
