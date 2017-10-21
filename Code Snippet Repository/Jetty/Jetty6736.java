    @Test
    public void testRsv3()
    {
        TextFrame frame = new TextFrame();
        frame.setPayload("Hi");
        frame.setRsv3(true);
        laxGenerator.setRsv3InUse(true);
        ByteBuffer actual = generateWholeFrame(laxGenerator,frame);
        String expected = "91024869";
        assertFrameHex("Lax Text Frame with RSV3",expected,actual);
    }
