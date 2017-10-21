    private void readIntoBuffer() {

        long readLength = availableLength - currentPosition;

        if (readLength <= 0) {
            return;
        }

        if (readLength > streamBlockSize) {
            readLength = streamBlockSize;
        }

        buffer = clob.getChars(session, currentPosition, (int) readLength);
        bufferOffset = currentPosition;
    }
