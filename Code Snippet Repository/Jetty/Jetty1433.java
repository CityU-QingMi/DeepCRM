    @Test
    public void test204() throws Exception
    {
        ByteBuffer header = BufferUtil.allocate(8096);
        ByteBuffer content = BufferUtil.toBuffer("0123456789");

        HttpGenerator gen = new HttpGenerator();
        
        MetaData.Response info = new MetaData.Response(HttpVersion.HTTP_1_1, 204, "Foo", new HttpFields(), 10);
        info.getFields().add("Content-Type", "test/data");
        info.getFields().add("Last-Modified", DateGenerator.__01Jan1970);

        HttpGenerator.Result result = gen.generateResponse(info, false, header, null, content, true);
     
        assertEquals(gen.isNoContent(), true);    
        assertEquals(HttpGenerator.Result.FLUSH, result);
        assertEquals(HttpGenerator.State.COMPLETING, gen.getState());
        String responseheaders = BufferUtil.toString(header);
        BufferUtil.clear(header);     

        result = gen.generateResponse(null, false, null, null, content, false);
        assertEquals(HttpGenerator.Result.DONE, result);
        assertEquals(HttpGenerator.State.END, gen.getState());
        
        assertThat(responseheaders, containsString("HTTP/1.1 204 Foo"));
        assertThat(responseheaders, containsString("Last-Modified: Thu, 01 Jan 1970 00:00:00 GMT"));
        assertThat(responseheaders, not(containsString("Content-Length: 10")));

        //Note: the HttpConnection.process() method is responsible for actually
        //excluding the content from the response based on generator.isNoContent()==true
    }
