    public ByteBuffer generate(ByteBufferPool.Lease lease, FrameType frameType, int capacity, int length, int flags, int streamId)
    {
        ByteBuffer header = lease.acquire(capacity, true);
        header.put((byte)((length & 0x00_FF_00_00) >>> 16));
        header.put((byte)((length & 0x00_00_FF_00) >>> 8));
        header.put((byte)((length & 0x00_00_00_FF)));
        header.put((byte)frameType.getType());
        header.put((byte)flags);
        header.putInt(streamId);
        return header;
    }
