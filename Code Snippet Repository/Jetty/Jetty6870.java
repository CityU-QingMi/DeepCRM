    @Test
    public void testParseUpgrade()
    {
        // Example from RFC6455 - Section 1.2 (Protocol Overview)
        StringBuilder resp = new StringBuilder();
        resp.append("HTTP/1.1 101 Switching Protocols\r\n");
        resp.append("Upgrade: websocket\r\n");
        resp.append("Connection: Upgrade\r\n");
        resp.append("Sec-WebSocket-Accept: s3pPLMBiTxaQ9kYGzzhZRbK+xOo=\r\n");
        resp.append("Sec-WebSocket-Protocol: chat\r\n");
        resp.append("\r\n");

        ByteBuffer buf = BufferUtil.toBuffer(resp.toString(),StandardCharsets.UTF_8);

        HttpResponseParseCapture capture = new HttpResponseParseCapture();
        HttpResponseHeaderParser parser = new HttpResponseHeaderParser(capture);
        assertThat("Parser.parse",parser.parse(buf),notNullValue());
        assertThat("Response.statusCode",capture.getStatusCode(),is(101));
        assertThat("Response.statusReason",capture.getStatusReason(),is("Switching Protocols"));
        assertThat("Response.headers[Upgrade]",capture.getHeader("Upgrade"),is("websocket"));
        assertThat("Response.headers[Connection]",capture.getHeader("Connection"),is("Upgrade"));

        assertThat("Buffer.remaining",buf.remaining(),is(0));
    }
