    @Test
    public void testMultiByteOverflowUTF16x2() throws Exception
    {
        HttpWriter _writer = new Utf8HttpWriter(_httpOut);

        final String singleByteStr = "a";
        int remainSize = 1;
        final String multiByteDuplicateStr = "\uD842\uDF9F";
        int adjustSize = -1;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < HttpWriter.MAX_OUTPUT_CHARS + adjustSize; i++)
        {
            sb.append(singleByteStr);
        }
        sb.append(multiByteDuplicateStr);
        for (int i = 0; i < remainSize; i++)
        {
            sb.append(singleByteStr);
        }
        String source = sb.toString();

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
