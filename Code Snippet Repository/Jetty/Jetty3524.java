    @Test
    public void testUTF16x2() throws Exception
    {
        HttpWriter _writer = new Utf8HttpWriter(_httpOut);

        String source = "\uD842\uDF9F";

        byte[] bytes = source.getBytes(StandardCharsets.UTF_8);
        _writer.write(source.toCharArray(),0,source.toCharArray().length);

        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        java.io.OutputStreamWriter osw = new java.io.OutputStreamWriter(baos, StandardCharsets.UTF_8);
        osw.write(source.toCharArray(),0,source.toCharArray().length);
        osw.flush();

        myReportBytes(bytes);
        myReportBytes(baos.toByteArray());
        myReportBytes(BufferUtil.toArray(_bytes));

        assertArrayEquals(bytes,BufferUtil.toArray(_bytes));
        assertArrayEquals(baos.toByteArray(),BufferUtil.toArray(_bytes));
    }
