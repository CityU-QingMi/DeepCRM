    private ByteBuffer generateEndRequest(int request, boolean aborted)
    {
        request &= 0xFF_FF;
        ByteBuffer endRequestBuffer = byteBufferPool.acquire(8, false);
        BufferUtil.clearToFill(endRequestBuffer);
        endRequestBuffer.putInt(0x01_03_00_00 + request);
        endRequestBuffer.putInt(0x00_08_00_00);
        endRequestBuffer.putInt(aborted ? 1 : 0);
        endRequestBuffer.putInt(0);
        BufferUtil.flipToFlush(endRequestBuffer, 0);
        return endRequestBuffer;
    }
