    public void testParamPrecedence() throws Exception {
        request.setRequestURI("/context/someAction.action");
        request.setQueryString("id=22&name=John");

        AnchorTag anchor = new AnchorTag();
        anchor.setPageContext(pageContext);
        anchor.setIncludeParams("get");
        anchor.setEncode("%{false}");

        ParamTag paramTag = new ParamTag();
        paramTag.setPageContext(pageContext);
        paramTag.setName("id");
        paramTag.setValue("%{'33'}");

        anchor.doStartTag();
        paramTag.doStartTag();
        paramTag.doEndTag();
        anchor.doEndTag();

        assertEquals(wrapWithAnchor("/context/someAction.action?id=33&amp;name=John"), writer.getBuffer().toString());
    }
