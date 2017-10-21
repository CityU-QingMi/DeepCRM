    @Test
    public void testNotCESU8() throws Exception
    {
        HttpWriter _writer = new Utf8HttpWriter(_httpOut);
        String data="xxx\uD801\uDC00xxx";
        _writer.write(data);
        assertEquals("787878F0909080787878",TypeUtil.toHexString(BufferUtil.toArray(_bytes)));
        assertArrayEquals(data.getBytes(StandardCharsets.UTF_8),BufferUtil.toArray(_bytes));
        assertEquals(3+4+3,_bytes.remaining());

        Utf8StringBuilder buf = new Utf8StringBuilder();
        buf.append(BufferUtil.toArray(_bytes),0,_bytes.remaining());
        assertEquals(data,buf.toString());
    }
