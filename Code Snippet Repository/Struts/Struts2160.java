    public void testParamPrecedenceWithAnchor() throws Exception {
        request.setRequestURI("/context/someAction.action");
        request.setQueryString("id=22&name=John");

        URLTag urlTag = new URLTag();
        urlTag.setPageContext(pageContext);
        urlTag.setIncludeParams("get");
        urlTag.setEncode("%{false}");
        urlTag.setAnchor("testAnchor");

        ParamTag paramTag = new ParamTag();
        paramTag.setPageContext(pageContext);
        paramTag.setName("id");
        paramTag.setValue("%{'33'}");

        urlTag.doStartTag();
        paramTag.doStartTag();
        paramTag.doEndTag();
        urlTag.doEndTag();

        assertEquals("/context/someAction.action?id=33&amp;name=John#testAnchor", writer.getBuffer().toString());
    }
