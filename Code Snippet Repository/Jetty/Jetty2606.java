    @Ignore("")
    @Test
    public void testInvalidShamrock() throws Exception
    {
        _rule.setCode("405");
        _rule.setReason("foo");
        _request.setURIPathQuery("/jsp/shamrock-%00%E2%98%98.jsp");
        
        String result = _rule.matchAndApply(_request.getRequestURI(), _request, _response);

        assertEquals(405,_response.getStatus());
        assertEquals("foo",_response.getReason());
    }
