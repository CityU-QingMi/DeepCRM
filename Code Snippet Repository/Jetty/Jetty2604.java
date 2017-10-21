    @Test
    public void testInvalidUrlWithReason() throws Exception
    {
        _rule.setCode("405");
        _rule.setReason("foo");
        _request.setURIPathQuery("/%00/");
        
        String result = _rule.matchAndApply(_request.getRequestURI(), _request, _response);

        assertEquals(405,_response.getStatus());
        assertEquals("foo",_response.getReason());
    }
