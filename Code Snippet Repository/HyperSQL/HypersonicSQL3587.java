    public Object castToType(SessionInterface session, Object a,
                             Type otherType) {

        if (a == null) {
            return null;
        }

        if (otherType.typeCode == Types.SQL_BLOB) {
            BlobData b          = (BlobData) a;
            long     blobLength = b.length(session);

            if (blobLength > precision) {
                blobLength = precision;

                session.addWarning(Error.error(ErrorCode.W_01004));

                b = b.getBlob(session, 0, blobLength);

                return b;
            }

            return a;
        }

        if (otherType.typeCode == Types.SQL_BINARY
                || otherType.typeCode == Types.SQL_VARBINARY) {
            BlobData b          = (BlobData) a;
            long     blobLength = b.length(session);

            if (blobLength > precision) {
                blobLength = precision;

                session.addWarning(Error.error(ErrorCode.W_01004));
            }

            BlobData blob = session.createBlob(b.length(session));

            blob.setBytes(session, 0, b.getBytes(), 0, (int) blobLength);

            return blob;
        }

        throw Error.error(ErrorCode.X_42561);
    }
