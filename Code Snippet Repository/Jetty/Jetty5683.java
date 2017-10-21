    @Test
    public void testBasicParse()
    {
        ByteBuffer buf = ByteBuffer.allocate(64);
        appendUtf8(buf,"Hello World\n");
        BufferUtil.flipToFlush(buf,0);

        Utf8LineParser utfparser = new Utf8LineParser();

        String line = utfparser.parse(buf);
        Assert.assertThat("Line",line,is("Hello World"));
    }
