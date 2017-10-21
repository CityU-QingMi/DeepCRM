    @Test
    public void testStartEOF() throws Exception
    {
        HttpParser.RequestHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler);
        parser.atEOF();
        parser.parseNext(BufferUtil.EMPTY_BUFFER);

        Assert.assertTrue(_early);
        Assert.assertEquals(null, _bad);
    }
