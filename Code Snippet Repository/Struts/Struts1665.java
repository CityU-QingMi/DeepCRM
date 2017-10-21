    public void testGetServletPathWithRequestURIAndContextPathSet() throws Exception {
        expect(requestMock.getServletPath()).andStubReturn(null);
        expect(requestMock.getRequestURI()).andStubReturn("/servlet/mycontext/test.jsp");
        expect(requestMock.getContextPath()).andStubReturn("/servlet");
        expect(requestMock.getContextPath()).andStubReturn("/servlet");
        expect(requestMock.getPathInfo()).andStubReturn("test.jsp");
        expect(requestMock.getPathInfo()).andStubReturn("test.jsp");
        replay(requestMock);
        assertEquals("/mycontext/", RequestUtils.getServletPath(requestMock));
        verify(requestMock);
    }
