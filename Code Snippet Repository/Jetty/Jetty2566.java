    @Test
    public void testWithoutSsl() throws Exception
    {
        // disable SSL
        super.stop();
        super.start(false);

        HttpFields fields = _request.getHttpFields();
        fields.add("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT 5.0)");

        String result = _rule.matchAndApply(_request.getRequestURI(), _request, _response);

        assertEquals(null, result);
        assertEquals(null, _response.getHeader(HttpHeader.CONNECTION.asString()));
    }
