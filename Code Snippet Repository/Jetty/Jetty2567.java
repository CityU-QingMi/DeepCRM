    @Test
    public void testWin2kWithIE6() throws Exception
    {
        HttpFields fields = _request.getHttpFields();
        fields.add("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");

        String result = _rule.matchAndApply(_request.getRequestURI(), _request, _response);

        assertEquals(_request.getRequestURI(), result);
        assertEquals(HttpHeaderValue.CLOSE.asString(), _response.getHeader(HttpHeader.CONNECTION.asString()));
    }
