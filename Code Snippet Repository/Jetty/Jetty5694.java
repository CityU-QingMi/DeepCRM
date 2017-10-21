    @Test
    public void testUtfStringBuilder() throws Exception
    {
        String source = "abcd012345\n\r\u0000\u00a4\u10fb\ufffdjetty";
        byte[] bytes = source.getBytes(StandardCharsets.UTF_8);
        Utf8StringBuilder buffer = new Utf8StringBuilder();
        for (byte aByte : bytes)
            buffer.append(aByte);
        assertEquals(source,buffer.toString());
        assertTrue(buffer.toString().endsWith("jetty"));
    }
