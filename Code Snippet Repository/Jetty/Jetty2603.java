    @Test
    public void testInvalidUrl() throws Exception
    {
        _rule.setCode("404");
        _request.setURIPathQuery("/invalid%0c/uri.html");
        
        String result = _rule.matchAndApply(_request.getRequestURI(), _request, _response);

        assertEquals(404,_response.getStatus());
    }
