    @Test
    public void testEncodedRegex() throws Exception
    {
        _response.setStatus(200);
        _request.setHandled(false);
        _handler.setOriginalPathAttribute("/before");
        _handler.setRewriteRequestURI(true);
        _handler.setRewritePathInfo(false);
        _request.setURIPathQuery("/xxx/x%20y");
        _request.setPathInfo("/xxx/x y");
        _handler.handle("/xxx/x y",_request,_request, _response);
        assertEquals(201,_response.getStatus());
        assertEquals("/x y/zzz",_request.getAttribute("target"));
        assertEquals("/x%20y/zzz",_request.getAttribute("URI"));
        assertEquals("/xxx/x y",_request.getAttribute("info"));

    }
