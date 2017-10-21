    public void testResourceUrlWithNestedParam() throws Exception {
        mockHttpReq.stubs().method("getQueryString").will(returnValue(""));
        mockPortletRes.expects(once()).method("encodeURL").with(eq("/contextPath/image.gif?testParam1=testValue1")).will(returnValue("/contextPath/image.gif?testParam1=testValue1"));
        mockJspWriter.setExpectedData("/contextPath/image.gif?testParam1=testValue1");
        mockCtx.expects(atLeastOnce()).method("getMajorVersion").will(returnValue(1));

        ParamTag paramTag = new ParamTag();
        paramTag.setPageContext((PageContext)mockPageCtx.proxy());
        paramTag.setParent(tag);
        paramTag.setName("testParam1");
        paramTag.setValue("'testValue1'");
        tag.setValue("image.gif");
        tag.doStartTag();
        paramTag.doStartTag();
        paramTag.doEndTag();
        tag.doEndTag();
        mockJspWriter.verify();
    }
