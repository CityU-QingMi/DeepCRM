    @Test
    public void testNghttpx()
    {        
        // Response encoded by nghttpx
        String encoded="886196C361Be940b6a65B6850400B8A00571972e080a62D1Bf5f87497cA589D34d1f9a0f0d0234327690Aa69D29aFcA954D3A5358980Ae112e0f7c880aE152A9A74a6bF3";
        ByteBuffer buffer = ByteBuffer.wrap(TypeUtil.fromHexString(encoded));

        HpackDecoder decoder = new HpackDecoder(4096,8192);
        MetaData.Response response = (MetaData.Response)decoder.decode(buffer);

        assertThat(response.getStatus(),is(200));
        assertThat(response.getFields().size(),is(6));
        assertTrue(response.getFields().contains(new HttpField(HttpHeader.DATE,"Fri, 15 Jul 2016 02:36:20 GMT")));
        assertTrue(response.getFields().contains(new HttpField(HttpHeader.CONTENT_TYPE,"text/html")));
        assertTrue(response.getFields().contains(new HttpField(HttpHeader.CONTENT_ENCODING,"")));
        assertTrue(response.getFields().contains(new HttpField(HttpHeader.CONTENT_LENGTH,"42")));
        assertTrue(response.getFields().contains(new HttpField(HttpHeader.SERVER,"nghttpx nghttp2/1.12.0")));
        assertTrue(response.getFields().contains(new HttpField(HttpHeader.VIA,"1.1 nghttpx")));
    }
