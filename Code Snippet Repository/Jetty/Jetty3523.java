    @Test
    public void testMultiByteOverflowUTF8() throws Exception
    {
        HttpWriter _writer = new Utf8HttpWriter(_httpOut);
        final String singleByteStr = "a";
        final String multiByteDuplicateStr = "\uFF22";
        int remainSize = 1;

        int multiByteStrByteLength = multiByteDuplicateStr.getBytes(StandardCharsets.UTF_8).length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < HttpWriter.MAX_OUTPUT_CHARS - multiByteStrByteLength; i++) {
          sb.append(singleByteStr);
        }
        sb.append(multiByteDuplicateStr);
        for (int i = 0; i < remainSize; i++) {
          sb.append(singleByteStr);
        }
        char[] buf = new char[HttpWriter.MAX_OUTPUT_CHARS * 3];

        int length = HttpWriter.MAX_OUTPUT_CHARS - multiByteStrByteLength + remainSize + 1;
        sb.toString().getChars(0, length, buf, 0);

        _writer.write(buf, 0, length);

        assertEquals(sb.toString(),new String(BufferUtil.toArray(_bytes),StandardCharsets.UTF_8));
    }
