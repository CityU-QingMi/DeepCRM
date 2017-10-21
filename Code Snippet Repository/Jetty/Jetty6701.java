    @Test
    public void testText_Masked()
    {
        WebSocketFrame frame = new TextFrame().setPayload("Hello");
        byte maskingKey[] = Hex.asByteArray("11223344");
        frame.setMask(maskingKey);

        // what is expected
        StringBuilder expected = new StringBuilder();
        expected.append("8185").append("11223344");
        expected.append(asMaskedHex("Hello",maskingKey));

        // validate
        assertGeneratedBytes(expected,frame);
    }
