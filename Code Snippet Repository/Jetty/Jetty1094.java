    public int generateSettings(ByteBufferPool.Lease lease, Map<Integer, Integer> settings, boolean reply)
    {
        // Two bytes for the identifier, four bytes for the value.
        int entryLength = 2 + 4;
        int length = entryLength * settings.size();
        if (length > getMaxFrameSize())
            throw new IllegalArgumentException("Invalid settings, too big");

        ByteBuffer header = generateHeader(lease, FrameType.SETTINGS, length, reply ? Flags.ACK : Flags.NONE, 0);

        for (Map.Entry<Integer, Integer> entry : settings.entrySet())
        {
            header.putShort(entry.getKey().shortValue());
            header.putInt(entry.getValue());
        }

        BufferUtil.flipToFlush(header, 0);
        lease.append(header, true);

        return Frame.HEADER_LENGTH + length;
    }
