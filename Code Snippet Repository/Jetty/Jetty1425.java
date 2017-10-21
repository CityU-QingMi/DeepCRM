    @Test
    public void testHTTP() throws Exception
    {
        Handler handler = new Handler();

        HttpGenerator gen = new HttpGenerator();

        String t = run.toString();

        run.result.getHttpFields().clear();

        String response = run.result.build(run.httpVersion, gen, "OK\r\nTest", run.connection.val, null, run.chunks);

        HttpParser parser = new HttpParser(handler);
        parser.setHeadResponse(run.result._head);

        parser.parseNext(BufferUtil.toBuffer(response));

        if (run.result._body != null)
            assertEquals(t, run.result._body, this._content);

        if (run.httpVersion == 10)
            assertTrue(t, gen.isPersistent() || run.result._contentLength >= 0 || EnumSet.of(ConnectionType.CLOSE, ConnectionType.KEEP_ALIVE, ConnectionType.NONE).contains(run.connection));
        else
            assertTrue(t, gen.isPersistent() || EnumSet.of(ConnectionType.CLOSE, ConnectionType.TE_CLOSE).contains(run.connection));

        assertEquals("OK??Test", _reason);

        if (_content == null)
            assertTrue(t, run.result._body == null);
        else
            assertThat(t, run.result._contentLength, either(equalTo(_content.length())).or(equalTo(-1)));
    }
