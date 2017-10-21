    @Test
    public void testUTF32codes() throws Exception
    {
        String source = "\uD842\uDF9F";
        byte[] bytes = source.getBytes(StandardCharsets.UTF_8);

        String jvmcheck = new String(bytes,0,bytes.length,StandardCharsets.UTF_8);
        assertEquals(source,jvmcheck);

        Utf8StringBuffer buffer = new Utf8StringBuffer();
        buffer.append(bytes,0,bytes.length);
        String result = buffer.toString();
        assertEquals(source,result);
    }
