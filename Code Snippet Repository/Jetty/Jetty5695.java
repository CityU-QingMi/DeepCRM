    @Test(expected = IllegalArgumentException.class)
    public void testShort() throws Exception
    {
        String source = "abc\u10fb";
        byte[] bytes = source.getBytes(StandardCharsets.UTF_8);
        Utf8StringBuilder buffer = new Utf8StringBuilder();
        for (int i = 0; i < bytes.length - 1; i++)
            buffer.append(bytes[i]);
        buffer.toString();
    }
