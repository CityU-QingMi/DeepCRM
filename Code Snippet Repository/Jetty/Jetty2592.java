    @Test
    public void testEncodedPattern() throws Exception
    {
        _response.setStatus(200);
        _request.setHandled(false);
        _handler.setOriginalPathAttribute("/before");
        _handler.setRewriteRequestURI(true);
        _handler.setRewritePathInfo(false);
        _request.setURIPathQuery("/ccc/x%20y");
        _request.setPathInfo("/ccc/x y");
        _handler.handle("/ccc/x y",_request,_request, _response);
        assertEquals(201,_response.getStatus());
        assertEquals("/ddd/x y",_request.getAttribute("target"));
        assertEquals("/ddd/x%20y",_request.getAttribute("URI"));
        assertEquals("/ccc/x y",_request.getAttribute("info"));

    }
