    @Test
    public void testRsv1()
    {
        TextFrame frame = new TextFrame();
        frame.setPayload("Hi");
        frame.setRsv1(true);
        laxGenerator.setRsv1InUse(true);
        ByteBuffer actual = generateWholeFrame(laxGenerator,frame);
        String expected = "C1024869";
        assertFrameHex("Lax Text Frame with RSV1",expected,actual);
    }
