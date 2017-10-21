    @Test
    public void testWinXpWithIE7() throws Exception
    {
        HttpFields fields = _request.getHttpFields();
        fields.add("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");

        String result = _rule.matchAndApply(_request.getRequestURI(), _request, _response);

        assertEquals(null, result);
        assertEquals(null, _response.getHeader(HttpHeader.CONNECTION.asString()));
    }
