    public BlobData overlay(Session session, BlobData data, BlobData overlay,
                            long offset, long length, boolean hasLength) {

        if (data == null || overlay == null) {
            return null;
        }

        if (!hasLength) {
            length = overlay.length(session);
        }

        switch (typeCode) {

            case Types.SQL_BINARY :
            case Types.SQL_VARBINARY : {
                BinaryData binary =
                    new BinaryData(session,
                                   substring(session, data, 0, offset, true),
                                   overlay);

                binary = new BinaryData(session, binary,
                                        substring(session, data,
                                                  offset + length, 0, false));

                return binary;
            }
            case Types.SQL_BLOB : {
                byte[] bytes = substring(session, data, 0, offset,
                                         false).getBytes();
                long blobLength = data.length(session)
                                  + overlay.length(session) - length;
                BlobData blob = session.createBlob(blobLength);

                blob.setBytes(session, 0, bytes);
                blob.setBytes(session, blob.length(session),
                              overlay.getBytes());

                bytes = substring(session, data, offset + length, 0,
                                  false).getBytes();

                blob.setBytes(session, blob.length(session), bytes);

                return blob;
            }
            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "BinaryType");
        }
    }
