    public void testGetServletPathWithRequestURIAndEmptyContextPath() throws Exception {
        expect(requestMock.getServletPath()).andStubReturn(null);
        expect(requestMock.getRequestURI()).andStubReturn("/mycontext/test.jsp");
        expect(requestMock.getContextPath()).andStubReturn("");
        expect(requestMock.getPathInfo()).andStubReturn("test.jsp");
        expect(requestMock.getPathInfo()).andStubReturn("test.jsp");
        replay(requestMock);
        assertEquals("/mycontext/", RequestUtils.getServletPath(requestMock));
        verify(requestMock);
    }
