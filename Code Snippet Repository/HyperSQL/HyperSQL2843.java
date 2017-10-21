    public long position(SessionInterface session, BlobData data,
                         BlobData otherData, Type otherType, long offset) {

        if (data == null || otherData == null) {
            return -1L;
        }

        long otherLength = data.length(session);

        if (offset + otherLength > data.length(session)) {
            return -1;
        }

        return data.position(session, otherData, offset);
    }
