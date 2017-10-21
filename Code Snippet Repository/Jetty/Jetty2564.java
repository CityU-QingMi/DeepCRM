    @Test
    public void testWinVistaWithIE6() throws Exception
    {
        HttpFields fields = _request.getHttpFields();
        fields.add("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 6.0)");

        String result = _rule.matchAndApply(_request.getRequestURI(), _request, _response);

        assertEquals(null, result);
        assertEquals(null, _response.getHeader(HttpHeader.CONNECTION.asString()));
    }
