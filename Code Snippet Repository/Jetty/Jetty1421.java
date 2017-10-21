    @Test
    public void testGETRequestNoContent() throws Exception
    {
        ByteBuffer header=BufferUtil.allocate(2048);
        HttpGenerator gen = new HttpGenerator();

        HttpGenerator.Result
        result=gen.generateRequest(null,null,null,null, true);
        Assert.assertEquals(HttpGenerator.Result.NEED_INFO, result);
        Assert.assertEquals(HttpGenerator.State.START, gen.getState());

        Info info = new Info("GET","/index.html");
        info.getFields().add("Host","something");
        info.getFields().add("User-Agent","test");
        Assert.assertTrue(!gen.isChunking());

        result=gen.generateRequest(info,null,null,null, true);
        Assert.assertEquals(HttpGenerator.Result.NEED_HEADER, result);
        Assert.assertEquals(HttpGenerator.State.START, gen.getState());

        result=gen.generateRequest(info,header,null,null, true);
        Assert.assertEquals(HttpGenerator.Result.FLUSH, result);
        Assert.assertEquals(HttpGenerator.State.COMPLETING, gen.getState());
        Assert.assertTrue(!gen.isChunking());
        String out = BufferUtil.toString(header);
        BufferUtil.clear(header);

        result=gen.generateResponse(null,false,null,null, null, false);
        Assert.assertEquals(HttpGenerator.Result.DONE, result);
        Assert.assertEquals(HttpGenerator.State.END, gen.getState());
        Assert.assertTrue(!gen.isChunking());

        Assert.assertEquals(0, gen.getContentPrepared());
        Assert.assertThat(out, Matchers.containsString("GET /index.html HTTP/1.1"));
        Assert.assertThat(out, Matchers.not(Matchers.containsString("Content-Length")));
    }
