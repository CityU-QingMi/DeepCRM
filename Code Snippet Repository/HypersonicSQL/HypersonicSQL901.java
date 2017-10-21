    private void allocateBlobSegments(ResultLob result,
                                      InputStream stream) throws IOException {

        //
        long currentOffset = result.getOffset();
        int  bufferLength  = session.getStreamBlockSize();
        HsqlByteArrayOutputStream byteArrayOS =
            new HsqlByteArrayOutputStream(bufferLength);

        while (true) {
            byteArrayOS.reset();
            byteArrayOS.write(stream, bufferLength);

            if (byteArrayOS.size() == 0) {
                return;
            }

            byte[] byteArray = byteArrayOS.getBuffer();
            Result actionResult =
                database.lobManager.setBytes(result.getLobID(), currentOffset,
                                             byteArray, byteArrayOS.size());

            // FIXME: actionResult not used anymore!?
            currentOffset += byteArrayOS.size();

            if (byteArrayOS.size() < bufferLength) {
                return;
            }
        }
    }
