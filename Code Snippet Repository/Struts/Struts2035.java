    public void testParamPrecedenceWithAnchor() throws Exception {
        request.setRequestURI("/context/someAction.action");
        request.setQueryString("id=22&name=John");

        AnchorTag anchorTag = new AnchorTag();
        anchorTag.setPageContext(pageContext);
        anchorTag.setIncludeParams("get");
        anchorTag.setEncode("%{false}");
        anchorTag.setAnchor("testAnchor");

        ParamTag paramTag = new ParamTag();
        paramTag.setPageContext(pageContext);
        paramTag.setName("id");
        paramTag.setValue("%{'33'}");

        anchorTag.doStartTag();
        paramTag.doStartTag();
        paramTag.doEndTag();
        anchorTag.doEndTag();

        assertEquals(wrapWithAnchor("/context/someAction.action?id=33&amp;name=John#testAnchor"), writer.getBuffer().toString());
    }
