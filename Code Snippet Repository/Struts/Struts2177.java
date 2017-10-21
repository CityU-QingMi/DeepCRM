    public void testAddParameters() throws Exception {
        request.setAttribute("struts.request_uri", "/Test.action");

        request.setAttribute("struts.request_uri", "/TestAction.action");
        request.setQueryString("param0=value0");

        tag.doStartTag();
        tag.component.addParameter("param1", "value1");
        tag.component.addParameter("param2", "value2");
        tag.doEndTag();
        assertEquals("/TestAction.action?param0=value0&amp;param1=value1&amp;param2=value2", writer.toString());
    }
