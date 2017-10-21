    @Test
    public void testRsv2()
    {
        TextFrame frame = new TextFrame();
        frame.setPayload("Hi");
        frame.setRsv2(true);
        laxGenerator.setRsv2InUse(true);
        ByteBuffer actual = generateWholeFrame(laxGenerator,frame);
        String expected = "A1024869";
        assertFrameHex("Lax Text Frame with RSV2",expected,actual);
    }
