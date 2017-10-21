    @Test
    public void testValidUrl() throws Exception
    {
        _rule.setCode("404");
        _request.setURIPathQuery("/valid/uri.html");
        
        _rule.matchAndApply(_request.getRequestURI(), _request, _response);

        assertEquals(200,_response.getStatus());
    }
