    private void generateFrame(ByteBufferPool.Lease lease, int streamId, ByteBuffer data, boolean last)
    {
        int length = data.remaining();

        int flags = Flags.NONE;
        if (last)
            flags |= Flags.END_STREAM;

        ByteBuffer header = headerGenerator.generate(lease, FrameType.DATA, Frame.HEADER_LENGTH + length, length, flags, streamId);
        BufferUtil.flipToFlush(header, 0);
        lease.append(header, true);
        // Skip empty data buffers.
        if (data.remaining() > 0)
            lease.append(data, false);
    }
