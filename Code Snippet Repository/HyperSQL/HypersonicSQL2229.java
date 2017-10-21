    public void padCurrentBlock() throws IOException {

        int padBytes = bytesLeftInBlock();

        if (padBytes == 0) {
            return;
        }

        write(ZERO_BLOCK, padBytes);

        // REMOVE THIS DEV-ASSERTION:
        assertAtBlockBoundary();
    }
