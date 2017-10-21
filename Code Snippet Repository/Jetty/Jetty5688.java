    @Test(expected = Utf8Appendable.NotUtf8Exception.class)
    public void testUtf8WithAdditionalByte() throws Exception
    {
        String source = "abcXX";
        byte[] bytes = source.getBytes(StandardCharsets.UTF_8);
        bytes[3] = (byte)0xc0;
        bytes[4] = (byte)0x00;

        Utf8StringBuffer buffer = new Utf8StringBuffer();
        for (byte aByte : bytes)
            buffer.append(aByte);
    }
