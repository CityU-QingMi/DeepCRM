    @Test
    public void testConnectionKeepAliveWithAdditionalCustomValue() throws Exception
    {
        HttpGenerator generator = new HttpGenerator();

        HttpFields fields = new HttpFields();
        fields.put(HttpHeader.CONNECTION, HttpHeaderValue.KEEP_ALIVE);
        String customValue = "test";
        fields.add(HttpHeader.CONNECTION, customValue);
        MetaData.Response info = new MetaData.Response(HttpVersion.HTTP_1_0, 200, "OK", fields, -1);
        ByteBuffer header = BufferUtil.allocate(4096);
        HttpGenerator.Result result = generator.generateResponse(info, false, header, null, null, true);
        Assert.assertSame(HttpGenerator.Result.FLUSH, result);
        String headers = BufferUtil.toString(header);
        Assert.assertTrue(headers.contains(HttpHeaderValue.KEEP_ALIVE.asString()));
        Assert.assertTrue(headers.contains(customValue));
    }
