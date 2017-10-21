    @Ignore("")
    @Test
    public void testValidShamrock() throws Exception
    {
        _rule.setCode("405");
        _rule.setReason("foo");
        _request.setURIPathQuery("/jsp/shamrock-%E2%98%98.jsp");
        
        String result = _rule.matchAndApply(_request.getRequestURI(), _request, _response);

        assertEquals(200,_response.getStatus());
    }
