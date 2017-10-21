    @Test
    public void testDecodeD_4()
    {
        HpackDecoder decoder = new HpackDecoder(4096,8192);

        // First request
        String encoded="828684418cf1e3c2e5f23a6ba0ab90f4ff";
        ByteBuffer buffer = ByteBuffer.wrap(TypeUtil.fromHexString(encoded));

        MetaData.Request request = (MetaData.Request)decoder.decode(buffer);

        assertEquals("GET", request.getMethod());
        assertEquals(HttpScheme.HTTP.asString(),request.getURI().getScheme());
        assertEquals("/",request.getURI().getPath());
        assertEquals("www.example.com",request.getURI().getHost());
        assertFalse(request.iterator().hasNext());

        // Second request
        encoded="828684be5886a8eb10649cbf";
        buffer = ByteBuffer.wrap(TypeUtil.fromHexString(encoded));

        request = (MetaData.Request)decoder.decode(buffer);

        assertEquals("GET", request.getMethod());
        assertEquals(HttpScheme.HTTP.asString(),request.getURI().getScheme());
        assertEquals("/",request.getURI().getPath());
        assertEquals("www.example.com",request.getURI().getHost());
        Iterator<HttpField> iterator=request.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(new HttpField("cache-control","no-cache"),iterator.next());
        assertFalse(iterator.hasNext());
    }
