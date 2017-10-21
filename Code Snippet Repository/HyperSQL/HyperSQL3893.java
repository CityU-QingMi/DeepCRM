    private void allocateClobSegments(long lobID, long offset,
                                      Reader reader) throws IOException {

        int             bufferLength  = session.getStreamBlockSize();
        CharArrayWriter charWriter    = new CharArrayWriter(bufferLength);
        long            currentOffset = offset;

        while (true) {
            charWriter.reset();
            charWriter.write(reader, bufferLength);

            char[] charArray = charWriter.getBuffer();

            if (charWriter.size() == 0) {
                return;
            }

            Result actionResult = database.lobManager.setChars(lobID,
                currentOffset, charArray, charWriter.size());

            // FIXME: actionResult not used anymore!?
            currentOffset += charWriter.size();

            if (charWriter.size() < bufferLength) {
                return;
            }
        }
    }
