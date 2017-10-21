    private void positionBufferSeek(long offset) {

        if (offset < bufferPosition
                || offset >= bufferPosition + bufferLength) {
            setCurrentBuffer(offset);
        }

        buffer.position((int) (offset - bufferPosition));

        currentPosition = offset;
    }
