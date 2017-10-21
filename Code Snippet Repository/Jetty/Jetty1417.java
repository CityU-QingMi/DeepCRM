    @Test
    public void testCachedPut() throws Exception
    {
        HttpFields header = new HttpFields();

        header.put("Connection", "Keep-Alive");
        header.put("tRansfer-EncOding", "CHUNKED");
        header.put("CONTENT-ENCODING", "gZIP");

        ByteBuffer buffer = BufferUtil.allocate(1024);
        BufferUtil.flipToFill(buffer);
        HttpGenerator.putTo(header,buffer);
        BufferUtil.flipToFlush(buffer,0);
        String out = BufferUtil.toString(buffer).toLowerCase(Locale.ENGLISH);

        Assert.assertThat(out,Matchers.containsString((HttpHeader.CONNECTION+": "+HttpHeaderValue.KEEP_ALIVE).toLowerCase(Locale.ENGLISH)));
        Assert.assertThat(out,Matchers.containsString((HttpHeader.TRANSFER_ENCODING+": "+HttpHeaderValue.CHUNKED).toLowerCase(Locale.ENGLISH)));
        Assert.assertThat(out,Matchers.containsString((HttpHeader.CONTENT_ENCODING+": "+HttpHeaderValue.GZIP).toLowerCase(Locale.ENGLISH)));
    }
