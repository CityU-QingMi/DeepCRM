    public int read() throws IOException {

        if (seekPosition >= fileLength) {
            return -1;
        }

        if (bufferDirty || seekPosition < bufferOffset
                || seekPosition >= bufferOffset + buffer.length) {
            readIntoBuffer();
        }

        ba.reset();
        ba.skip(seekPosition - bufferOffset);

        int val = ba.read();

        seekPosition++;

        return val;
    }
