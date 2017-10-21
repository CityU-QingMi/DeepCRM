    @Test
    public void testResponseBufferUpgradeFrom() throws Exception
    {
        ByteBuffer buffer = BufferUtil.toBuffer(
                "HTTP/1.1 101 Upgrade\r\n" +
                "Connection: upgrade\r\n" +
                "Content-Length: 0\r\n" +
                "Sec-WebSocket-Accept: 4GnyoUP4Sc1JD+2pCbNYAhFYVVA\r\n" +
                "\r\n" +
                "FOOGRADE");
    
        HttpParser.ResponseHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler);
    
        while (!parser.isState(State.END))
        {
            parser.parseNext(buffer);
        }
        
        Assert.assertThat(BufferUtil.toUTF8String(buffer), Matchers.is("FOOGRADE"));
    }
