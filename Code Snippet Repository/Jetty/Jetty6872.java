    @Test
    public void testDeMaskTextSliced()
    {
        final byte msgChar = '*';
        final int messageSize = 25;

        byte message[] = new byte[messageSize];
        Arrays.fill(message,msgChar);

        TextFrame frame = new TextFrame();
        frame.setPayload(ByteBuffer.wrap(message));
        frame.setMask(Hex.asByteArray("11223344"));

        ByteBuffer buf = UnitGenerator.generate(frame);
        LOG.debug("Buf: {}",BufferUtil.toDetailString(buf));
        ByteBuffer payload = buf.slice();
        payload.position(6); // where payload starts
        
        LOG.debug("Payload: {}",BufferUtil.toDetailString(payload));
        LOG.debug("Pre-Processed: {}",Hex.asHex(payload));

        DeMaskProcessor demask = new DeMaskProcessor();
        demask.reset(frame);
        ByteBuffer slice1 = payload.slice();
        ByteBuffer slice2 = payload.slice();

        // slice at non-multiple of 4, but also where last buffer remaining
        // is more than 4.
        int slicePoint = 7;
        slice1.limit(slicePoint);
        slice2.position(slicePoint);

        Assert.assertThat("Slices are setup right",slice1.remaining() + slice2.remaining(),is(messageSize));

        demask.process(slice1);
        demask.process(slice2);
        
        LOG.debug("Post-Processed: {}",Hex.asHex(payload));

        Assert.assertThat("Payload.remaining",payload.remaining(),is(messageSize));
        for (int i = payload.position(); i < payload.limit(); i++)
        {
            Assert.assertThat("payload[" + i + "]",payload.get(i),is(msgChar));
        }
    }
