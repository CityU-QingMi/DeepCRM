    private void setCurrentBuffer(long offset) {

        if(readOnly) {
            return;
        }

        int bufferIndex = (int) (offset >> largeBufferScale);

        // when moving to last position in file
        if (bufferIndex == buffers.length) {
            bufferIndex    = buffers.length - 1;
            bufferPosition = (long) bufferIndex * largeBufferSize;
            buffer         = buffers[bufferIndex];

            return;
        }

        buffer         = buffers[bufferIndex];
        bufferPosition = offset & largeBufferMask;
    }
