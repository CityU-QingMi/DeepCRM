    public int generatePushPromise(ByteBufferPool.Lease lease, int streamId, int promisedStreamId, MetaData metaData)
    {
        if (streamId < 0)
            throw new IllegalArgumentException("Invalid stream id: " + streamId);
        if (promisedStreamId < 0)
            throw new IllegalArgumentException("Invalid promised stream id: " + promisedStreamId);

        int maxFrameSize = getMaxFrameSize();
        // The promised streamId space.
        int extraSpace = 4;
        maxFrameSize -= extraSpace;

        ByteBuffer hpacked = lease.acquire(maxFrameSize, false);
        BufferUtil.clearToFill(hpacked);
        encoder.encode(hpacked, metaData);
        int hpackedLength = hpacked.position();
        BufferUtil.flipToFlush(hpacked, 0);

        int length = hpackedLength + extraSpace;
        int flags = Flags.END_HEADERS;

        ByteBuffer header = generateHeader(lease, FrameType.PUSH_PROMISE, length, flags, streamId);
        header.putInt(promisedStreamId);
        BufferUtil.flipToFlush(header, 0);

        lease.append(header, true);
        lease.append(hpacked, true);

        return Frame.HEADER_LENGTH + length;
    }
