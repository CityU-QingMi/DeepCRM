    public void testIncludeRelative2Dots() throws Exception {
        // TODO: we should test for .. in unit test - is this test correct?
        // use always matcher as we can not determine the exact objects used in mock.include(request, response) call
        mockRequestDispatcher.include(anyObject(ServletRequest.class), anyObject(ServletResponse.class));
        expectLastCall().times(1);
        
        replay(mockRequestDispatcher);

        request.setupGetServletPath("app/manager");
        tag.setValue("../car/view.jsp");
        tag.doStartTag();
        tag.doEndTag();


        assertEquals("/car/view.jsp", request.getRequestDispatherString());
        assertEquals("", writer.toString());
        
        verify(mockRequestDispatcher);        
    }
