    @Test
    public void testManyMasked()
    {
        int pingCount = 2;

        // Prepare frames
        WebSocketFrame[] frames = new WebSocketFrame[pingCount + 1];
        for (int i = 0; i < pingCount; i++)
        {
            frames[i] = new PingFrame().setPayload(String.format("ping-%d",i));
        }
        frames[pingCount] = new CloseInfo(StatusCode.NORMAL).asFrame();

        // Mask All Frames
        byte maskingKey[] = Hex.asByteArray("11223344");
        for (WebSocketFrame f : frames)
        {
            f.setMask(maskingKey);
        }

        // Validate result of generation
        StringBuilder expected = new StringBuilder();
        expected.append("8986").append("11223344");
        expected.append(asMaskedHex("ping-0",maskingKey)); // ping 0
        expected.append("8986").append("11223344");
        expected.append(asMaskedHex("ping-1",maskingKey)); // ping 1
        expected.append("8882").append("11223344");
        byte closure[] = Hex.asByteArray("03E8");
        mask(closure,maskingKey);
        expected.append(Hex.asHex(closure)); // normal closure

        assertGeneratedBytes(expected,frames);
    }
