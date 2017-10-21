    public int generateReset(ByteBufferPool.Lease lease, int streamId, int error)
    {
        if (streamId < 0)
            throw new IllegalArgumentException("Invalid stream id: " + streamId);

        ByteBuffer header = generateHeader(lease, FrameType.RST_STREAM, ResetFrame.RESET_LENGTH, Flags.NONE, streamId);
        header.putInt(error);
        BufferUtil.flipToFlush(header, 0);
        lease.append(header, true);

        return Frame.HEADER_LENGTH + ResetFrame.RESET_LENGTH;
    }
