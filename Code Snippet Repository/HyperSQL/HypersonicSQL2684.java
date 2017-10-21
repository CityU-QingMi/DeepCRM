    public int readInt() throws IOException {

        if (bufferDirty || seekPosition < bufferOffset
                || seekPosition >= bufferOffset + buffer.length) {
            readIntoBuffer();
        }

        ba.reset();
        ba.skip(seekPosition - bufferOffset);

        int val = ba.readInt();

        seekPosition += 4;

        return val;
    }
