    @Test
    public void testDeMaskText()
    {
        // Use a string that is not multiple of 4 in length to test if/else branches in DeMaskProcessor
        String message = "0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF01";

        WebSocketFrame frame = new TextFrame().setPayload(message);
        frame.setMask(TypeUtil.fromHexString("11223344"));

        ByteBuffer buf = UnitGenerator.generate(frame);
        LOG.debug("Buf: {}",BufferUtil.toDetailString(buf));
        ByteBuffer payload = buf.slice();
        payload.position(6); // where payload starts
        LOG.debug("Payload: {}",BufferUtil.toDetailString(payload));

        DeMaskProcessor demask = new DeMaskProcessor();
        demask.reset(frame);
        demask.process(payload);

        ByteBufferAssert.assertEquals("DeMasked Text Payload",message,payload);
    }
