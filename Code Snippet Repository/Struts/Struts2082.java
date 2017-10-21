    public void testIncludeNoParam() throws Exception {
        
        // use always matcher as we can not determine the excact objects used in mock.include(request, response) call
        mockRequestDispatcher.include(anyObject(ServletRequest.class), anyObject(ServletResponse.class));
        expectLastCall().times(1);
        
        replay(mockRequestDispatcher);

        tag.setValue("person/list.jsp");
        tag.doStartTag();
        tag.doEndTag();
        
        assertEquals("/person/list.jsp", request.getRequestDispatherString());
        assertEquals("", writer.toString());
        
        verify(mockRequestDispatcher);
    }
