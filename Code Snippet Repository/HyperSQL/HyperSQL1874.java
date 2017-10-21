    public void read(byte[] b, int offset, int length) throws IOException {

        if (bufferDirty || seekPosition < bufferOffset
                || seekPosition >= bufferOffset + buffer.length) {
            readIntoBuffer();
        }

        ba.reset();
        ba.skip(seekPosition - bufferOffset);

        int bytesRead = ba.read(b, offset, length);

        seekPosition += bytesRead;

        if (bytesRead < length) {
            if (seekPosition != realPosition) {
                fileSeek(seekPosition);
            }

            file.readFully(b, offset + bytesRead, length - bytesRead);

            seekPosition += (length - bytesRead);
            realPosition = seekPosition;
        }
    }
