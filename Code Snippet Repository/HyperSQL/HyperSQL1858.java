    private void readIntoBuffer() throws IOException {

        long filePos    = seekPosition & bufferMask;
        long readLength = fileLength - filePos;

        if (readLength > buffer.length) {
            readLength = buffer.length;
        }

        if (readLength < 0) {
            throw new IOException("read beyond end of file");
        }

        try {
            file.seek(filePos);
            file.readFully(buffer, 0, (int) readLength);

            bufferOffset = filePos;
        } catch (IOException e) {
            resetPointer();
            logger.logWarningEvent("Read Error " + filePos + " " + readLength,
                                   e);

            throw e;
        }
    }
