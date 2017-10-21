    public void testAddParameters2() throws Exception {
        request.setAttribute("struts.request_uri", "/TestAction.action");
        request.setQueryString("param0=value0");

        tag.doStartTag();
        ParamTag param1 = new ParamTag();
        param1.setPageContext(pageContext);
        param1.setName("param1");
        param1.setValue("%{'value1'}");
        param1.doStartTag();
        param1.doEndTag();

        ParamTag param2 = new ParamTag();
        param2.setPageContext(pageContext);
        param2.setName("param2");
        param2.setValue("%{'value2'}");
        param2.doStartTag();
        param2.doEndTag();

        tag.doEndTag();
        String result = writer.toString();
        assertTrue(result.contains("param0=value0"));
        assertTrue(result.contains("param1=value1"));
        assertTrue(result.contains("param2=value2"));
        assertTrue(result.contains("/TestAction.action"));        
    }
