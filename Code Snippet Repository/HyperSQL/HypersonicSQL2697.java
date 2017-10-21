    private void positionBufferMove(int relOffset) {

        long offset = currentPosition + relOffset;

        if (offset >= bufferPosition + bufferLength) {
            setCurrentBuffer(offset);
        }

        buffer.position((int) (offset - bufferPosition));

        currentPosition = offset;
    }
