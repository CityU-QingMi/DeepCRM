    public void testGetResourceBase() throws Exception {
        expect(requestMock.getServletPath()).andReturn("/mycontext/");
        expect(requestMock.getRequestURI()).andReturn("/mycontext/");
        replay(requestMock);
        assertEquals("/mycontext", ResourceUtil.getResourceBase(requestMock));
        verify(requestMock);

        reset(requestMock);

        expect(requestMock.getServletPath()).andReturn("/mycontext/test.jsp");
        expect(requestMock.getRequestURI()).andReturn("/mycontext/test.jsp");
        replay(requestMock);
        
        assertEquals("/mycontext", ResourceUtil.getResourceBase(requestMock));
        verify(requestMock);

    }
