    public void setBytes(SessionInterface session, long pos, BlobData b,
                         long offset, long length) {

        if (length > Integer.MAX_VALUE) {
            throw new IndexOutOfBoundsException();
        }

        byte[] bytes = b.getBytes(session, offset, (int) length);

        setBytes(session, pos, bytes, 0, bytes.length);
    }
