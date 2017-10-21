    @Test(expected = IllegalArgumentException.class)
    public void testUtf8WithMissingByte() throws Exception
    {
        String source = "abc\u10fb";
        byte[] bytes = source.getBytes(StandardCharsets.UTF_8);
        Utf8StringBuffer buffer = new Utf8StringBuffer();
        for (int i = 0; i < bytes.length - 1; i++)
            buffer.append(bytes[i]);
        buffer.toString();
    }
