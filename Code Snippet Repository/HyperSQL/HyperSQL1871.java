    private void readIntoBuffer() throws IOException {

        long filePos = seekPosition;

        bufferDirty = false;

        long subOffset  = filePos % buffer.length;
        long readLength = fileLength - (filePos - subOffset);

        if (readLength <= 0) {
            throw new IOException("read beyond end of file");
        }

        if (readLength > buffer.length) {
            readLength = buffer.length;
        }

        fileSeek(filePos - subOffset);
        file.readFully(buffer, 0, (int) readLength);

        bufferOffset = filePos - subOffset;
        realPosition = bufferOffset + readLength;
    }
