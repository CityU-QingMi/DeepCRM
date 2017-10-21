    @Test
    public void testRequestWithContent() throws Exception
    {
        String out;
        ByteBuffer header=BufferUtil.allocate(4096);
        ByteBuffer content0=BufferUtil.toBuffer("Hello World. The quick brown fox jumped over the lazy dog.");
        HttpGenerator gen = new HttpGenerator();

        HttpGenerator.Result
        result=gen.generateRequest(null,null,null,content0, true);
        Assert.assertEquals(HttpGenerator.Result.NEED_INFO, result);
        Assert.assertEquals(HttpGenerator.State.START, gen.getState());

        Info info = new Info("POST","/index.html");
        info.getFields().add("Host","something");
        info.getFields().add("User-Agent","test");

        result=gen.generateRequest(info,null,null,content0, true);
        Assert.assertEquals(HttpGenerator.Result.NEED_HEADER, result);
        Assert.assertEquals(HttpGenerator.State.START, gen.getState());

        result=gen.generateRequest(info,header,null,content0, true);
        Assert.assertEquals(HttpGenerator.Result.FLUSH, result);
        Assert.assertEquals(HttpGenerator.State.COMPLETING, gen.getState());
        Assert.assertTrue(!gen.isChunking());
        out = BufferUtil.toString(header);
        BufferUtil.clear(header);
        out+=BufferUtil.toString(content0);
        BufferUtil.clear(content0);

        result=gen.generateResponse(null,false,null,null, null, false);
        Assert.assertEquals(HttpGenerator.Result.DONE, result);
        Assert.assertEquals(HttpGenerator.State.END, gen.getState());
        Assert.assertTrue(!gen.isChunking());


        Assert.assertThat(out, Matchers.containsString("POST /index.html HTTP/1.1"));
        Assert.assertThat(out, Matchers.containsString("Host: something"));
        Assert.assertThat(out, Matchers.containsString("Content-Length: 58"));
        Assert.assertThat(out, Matchers.containsString("Hello World. The quick brown fox jumped over the lazy dog."));

        Assert.assertEquals(58, gen.getContentPrepared());
    }
