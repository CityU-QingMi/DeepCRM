    private void fileSeek(long position) throws IOException {

        long skipPosition = realPosition;

        if (position < skipPosition) {
            resetStream();

            skipPosition = 0;
        }

        while (position > skipPosition) {
            skipPosition += file.skip(position - skipPosition);
        }
    }
