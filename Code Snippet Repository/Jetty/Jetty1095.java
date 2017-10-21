    public int generateWindowUpdate(ByteBufferPool.Lease lease, int streamId, int windowUpdate)
    {
        if (windowUpdate < 0)
            throw new IllegalArgumentException("Invalid window update: " + windowUpdate);

        ByteBuffer header = generateHeader(lease, FrameType.WINDOW_UPDATE, WindowUpdateFrame.WINDOW_UPDATE_LENGTH, Flags.NONE, streamId);
        header.putInt(windowUpdate);
        BufferUtil.flipToFlush(header, 0);
        lease.append(header, true);

        return Frame.HEADER_LENGTH + WindowUpdateFrame.WINDOW_UPDATE_LENGTH;
    }
