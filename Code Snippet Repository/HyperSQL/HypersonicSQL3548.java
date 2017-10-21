    public BlobData overlay(Session session, BlobData data, BlobData overlay,
                            long offset, long length, boolean hasLength) {

        if (data == null || overlay == null) {
            return null;
        }

        if (!hasLength) {
            length = overlay.length(session);
        }

        BinaryData binary =
            new BinaryData(session, substring(session, data, 0, offset, true),
                           overlay);

        binary = new BinaryData(session, binary,
                                substring(session, data, offset + length, 0,
                                          false));

        return binary;
    }
