    protected void readCompressedBlocks(int blocks) throws IOException {

        int bytesSoFar    = 0;
        int requiredBytes = 512 * blocks;

        // This method works with individual bytes!
        int i;

        while (bytesSoFar < requiredBytes) {
            i = readStream.read(readBuffer, bytesSoFar,
                                requiredBytes - bytesSoFar);

            if (i < 0) {
                throw new EOFException(
                    RB.decompression_ranout.getString(
                        bytesSoFar, requiredBytes));
            }

            bytesRead  += i;
            bytesSoFar += i;
        }
    }
