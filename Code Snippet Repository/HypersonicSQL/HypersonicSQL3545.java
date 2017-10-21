    public BlobData trim(Session session, BlobData data, int trim,
                         boolean leading, boolean trailing) {

        if (data == null) {
            return null;
        }

        long length = data.length(session);

        if (length > Integer.MAX_VALUE) {
            throw Error.error(ErrorCode.X_22027);
        }

        byte[] bytes    = data.getBytes(session, 0, (int) length);
        int    endindex = bytes.length;

        if (trailing) {
            for (--endindex; endindex >= 0 && bytes[endindex] == trim;
                    endindex--) {}

            endindex++;
        }

        int startindex = 0;

        if (leading) {
            while (startindex < endindex && bytes[startindex] == trim) {
                startindex++;
            }
        }

        byte[] newBytes = bytes;

        if (startindex != 0 || endindex != bytes.length) {
            newBytes = new byte[endindex - startindex];

            System.arraycopy(bytes, startindex, newBytes, 0,
                             endindex - startindex);
        }

        if (typeCode == Types.SQL_BLOB) {
            BlobData blob = session.createBlob(newBytes.length);

            blob.setBytes(session, 0, newBytes);

            return blob;
        } else {
            return new BinaryData(newBytes, newBytes == bytes);
        }
    }
