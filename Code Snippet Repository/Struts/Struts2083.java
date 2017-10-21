    public void testIncludeWithParameters() throws Exception {
       
        // use always matcher as we can not determine the excact objects used in mock.include(request, response) call
        mockRequestDispatcher.include(anyObject(ServletRequest.class), anyObject(ServletResponse.class));
        expectLastCall().times(1);
        
        replay(mockRequestDispatcher);

        tag.setValue("person/create.jsp");
        tag.doStartTag();
        // adding param must be done after doStartTag()
        Include include = (Include) tag.getComponent();
        include.addParameter("user", "Santa Claus");
        tag.doEndTag();

        assertEquals("/person/create.jsp?user=Santa+Claus", request.getRequestDispatherString());
        assertEquals("", writer.toString());
        
        verify(mockRequestDispatcher);
    }
