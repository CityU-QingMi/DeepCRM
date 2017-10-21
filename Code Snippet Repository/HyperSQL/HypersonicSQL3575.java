    public byte[] getBytes(SessionInterface session, long pos, int length) {

        long blobLength = length(session);

        if (pos >= blobLength) {
            return new byte[0];
        }

        if (pos + length >= blobLength) {
            length = (int) (blobLength - pos);
        }

        ResultLob resultOut = ResultLob.newLobGetBytesRequest(id, pos, length);
        Result    resultIn  = session.execute(resultOut);

        if (resultIn.isError()) {
            throw Error.error(resultIn);
        }

        return ((ResultLob) resultIn).getByteArray();
    }
