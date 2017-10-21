    public void testGetServletPathWithRequestURIAndContextPathSetButNoPatchInfo() throws Exception {
        expect(requestMock.getServletPath()).andStubReturn(null);
        expect(requestMock.getRequestURI()).andStubReturn("/servlet/mycontext/");
        expect(requestMock.getContextPath()).andStubReturn("/servlet");
        expect(requestMock.getContextPath()).andStubReturn("/servlet");
        expect(requestMock.getPathInfo()).andStubReturn(null);
        replay(requestMock);
        assertEquals("/mycontext/", RequestUtils.getServletPath(requestMock));
        verify(requestMock);
    }
