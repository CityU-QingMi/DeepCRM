    public Object concat(Session session, Object a, Object b) {

        if (a == null || b == null) {
            return null;
        }

        long length = ((BlobData) a).bitLength(session)
                      + ((BlobData) b).bitLength(session);

        if (length > Integer.MAX_VALUE) {
            throw Error.error(ErrorCode.W_01000);
        }

        byte[] aData   = ((BlobData) a).getBytes();
        byte[] bData   = ((BlobData) b).getBytes();
        int    aLength = (int) ((BlobData) a).bitLength(session);
        int    bLength = (int) ((BlobData) b).bitLength(session);
        byte[] bytes   = new byte[(int) (length + 7) / 8];

        System.arraycopy(aData, 0, bytes, 0, aData.length);

        for (int i = 0; i < bLength; i++) {
            if (BitMap.isSet(bData, i)) {
                BitMap.set(bytes, aLength + i);
            }
        }

        return new BinaryData(bytes, length);
    }
