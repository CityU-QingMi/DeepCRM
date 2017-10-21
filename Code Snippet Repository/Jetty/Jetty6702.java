    @Test
    public void testText_Masked_OffsetSourceByteBuffer()
    {
        ByteBuffer payload = ByteBuffer.allocate(100);
        payload.position(5);
        payload.put(StringUtil.getUtf8Bytes("Hello"));
        payload.flip();
        payload.position(5);
        // at this point, we have a ByteBuffer of 100 bytes.
        // but only a few bytes in the middle are made available for the payload.
        // we are testing that masking works as intended, even if the provided
        // payload does not start at position 0.
        LOG.debug("Payload = {}",BufferUtil.toDetailString(payload));
        WebSocketFrame frame = new TextFrame().setPayload(payload);
        byte maskingKey[] = Hex.asByteArray("11223344");
        frame.setMask(maskingKey);

        // what is expected
        StringBuilder expected = new StringBuilder();
        expected.append("8185").append("11223344");
        expected.append(asMaskedHex("Hello",maskingKey));

        // validate
        assertGeneratedBytes(expected,frame);
    }
