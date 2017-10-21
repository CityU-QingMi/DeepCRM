    private void fileSeek(long position) throws IOException {

        if (dataInput == null) {
            resetStream();
        }

        long skipPosition = realPosition;

        if (position < skipPosition) {
            resetStream();

            skipPosition = 0;
        }

        while (position > skipPosition) {
            skipPosition += dataInput.skip(position - skipPosition);
        }

        realPosition = position;
    }
