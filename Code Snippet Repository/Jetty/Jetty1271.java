    @Test
    public void testRequestWithPriorityWithContinuationFramesWithEmptyHeadersFrame() throws Exception
    {
        PriorityFrame priority = new PriorityFrame(1, 13, 200, true);
        testRequestWithContinuationFrames(null, () ->
        {
            ByteBufferPool.Lease lease = new ByteBufferPool.Lease(byteBufferPool);
            generator.control(lease, new PrefaceFrame());
            generator.control(lease, new SettingsFrame(new HashMap<>(), false));
            MetaData.Request metaData = newRequest("GET", new HttpFields());
            generator.control(lease, new HeadersFrame(1, metaData, priority, true));
            // Take the HeadersFrame header and set the length to just the priority frame.
            List<ByteBuffer> buffers = lease.getByteBuffers();
            ByteBuffer headersFrameHeader = buffers.get(2);
            headersFrameHeader.put(0, (byte)0);
            headersFrameHeader.putShort(1, (short)PriorityFrame.PRIORITY_LENGTH);
            // Insert a CONTINUATION frame header for the body of the HEADERS frame.
            lease.insert(3, buffers.get(4).slice(), false);
            return lease;
        });
    }
