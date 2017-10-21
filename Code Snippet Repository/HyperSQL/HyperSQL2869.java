    public void setBytes(SessionInterface session, long pos, byte[] bytes,
                         int offset, int len) {

        if (offset != 0 || len != bytes.length) {
            if (!BinaryData.isInLimits(bytes.length, offset, len)) {
                throw new IndexOutOfBoundsException();
            }

            byte[] newbytes = new byte[len];

            System.arraycopy(bytes, offset, newbytes, 0, len);

            bytes = newbytes;
        }

        ResultLob resultOut = ResultLob.newLobSetBytesRequest(id, pos, bytes);
        Result    resultIn  = session.execute(resultOut);

        if (resultIn.isError()) {
            throw resultIn.getException();
        }

        length = ((ResultLob) resultIn).getBlockLength();
    }
