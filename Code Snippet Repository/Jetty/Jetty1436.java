    @Test
    public void testResponseIncorrectContentLength() throws Exception
    {
        ByteBuffer header = BufferUtil.allocate(8096);

        HttpGenerator gen = new HttpGenerator();

        HttpGenerator.Result result = gen.generateResponse(null, false, null, null, null, true);
        assertEquals(HttpGenerator.Result.NEED_INFO, result);
        assertEquals(HttpGenerator.State.START, gen.getState());

        MetaData.Response info = new MetaData.Response(HttpVersion.HTTP_1_1, 200, null, new HttpFields(), 10);
        info.getFields().add("Last-Modified", DateGenerator.__01Jan1970);
        info.getFields().add("Content-Length", "11");

        result = gen.generateResponse(info, false, null, null, null, true);
        assertEquals(HttpGenerator.Result.NEED_HEADER, result);

        try
        {
            gen.generateResponse(info, false, header, null, null, true);
            Assert.fail();
        }
        catch(BadMessageException e)
        {
            assertEquals(e._code,500);
        }
    }
