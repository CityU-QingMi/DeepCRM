    private void checkBuffer() {

        if(readOnly) {
            return;
        }
        int bufferIndex = (int) (currentPosition >> largeBufferScale);

        if (currentPosition != bufferPosition + buffer.position()) {
            buffer         = buffers[bufferIndex];
            bufferPosition = currentPosition & largeBufferMask;

            buffer.position((int) (currentPosition - bufferPosition));
        } else if (buffer != buffers[bufferIndex]) {
            buffer = buffers[bufferIndex];
        }
    }
