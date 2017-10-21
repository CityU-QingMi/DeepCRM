    public int generateGoAway(ByteBufferPool.Lease lease, int lastStreamId, int error, byte[] payload)
    {
        if (lastStreamId < 0)
            throw new IllegalArgumentException("Invalid last stream id: " + lastStreamId);

        // The last streamId + the error code.
        int fixedLength = 4 + 4;

        // Make sure we don't exceed the default frame max length.
        int maxPayloadLength = Frame.DEFAULT_MAX_LENGTH - fixedLength;
        if (payload != null && payload.length > maxPayloadLength)
            payload = Arrays.copyOfRange(payload, 0, maxPayloadLength);

        int length = fixedLength + (payload != null ? payload.length : 0);
        ByteBuffer header = generateHeader(lease, FrameType.GO_AWAY, length, Flags.NONE, 0);

        header.putInt(lastStreamId);
        header.putInt(error);

        if (payload != null)
            header.put(payload);

        BufferUtil.flipToFlush(header, 0);
        lease.append(header, true);

        return Frame.HEADER_LENGTH + length;
    }
