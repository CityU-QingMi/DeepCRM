    @Test
    public void testComplexChars() throws Exception
    {
        ByteBuffer header = BufferUtil.allocate(8096);
        ByteBuffer content = BufferUtil.toBuffer("0123456789");

        HttpGenerator gen = new HttpGenerator();

        HttpGenerator.Result result = gen.generateResponse(null, false, null, null, content, true);
        assertEquals(HttpGenerator.Result.NEED_INFO, result);
        assertEquals(HttpGenerator.State.START, gen.getState());

        MetaData.Response info = new MetaData.Response(HttpVersion.HTTP_1_1, 200, "ØÆ", new HttpFields(), 10);
        info.getFields().add("Content-Type", "test/data;\r\nextra=value");
        info.getFields().add("Last-Modified", DateGenerator.__01Jan1970);

        result = gen.generateResponse(info, false, null, null, content, true);
        assertEquals(HttpGenerator.Result.NEED_HEADER, result);

        result = gen.generateResponse(info, false, header, null, content, true);
        assertEquals(HttpGenerator.Result.FLUSH, result);
        assertEquals(HttpGenerator.State.COMPLETING, gen.getState());
        String response = BufferUtil.toString(header);
        BufferUtil.clear(header);
        response += BufferUtil.toString(content);
        BufferUtil.clear(content);        

        result = gen.generateResponse(null, false, null, null, content, false);
        assertEquals(HttpGenerator.Result.DONE, result);
        assertEquals(HttpGenerator.State.END, gen.getState());
                
        assertEquals(10, gen.getContentPrepared());
        
        assertThat(response, containsString("HTTP/1.1 200 ØÆ"));
        assertThat(response, containsString("Last-Modified: Thu, 01 Jan 1970 00:00:00 GMT"));
        assertThat(response, containsString("Content-Type: test/data;  extra=value"));
        assertThat(response, containsString("Content-Length: 10"));
        assertThat(response, containsString("\r\n0123456789"));
    }
