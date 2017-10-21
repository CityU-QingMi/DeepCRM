    public void testResourceUrlWithTwoNestedParam() throws Exception {
        mockHttpReq.stubs().method("getQueryString").will(returnValue(""));
        mockPortletRes.expects(once()).method("encodeURL").with(eq("/contextPath/image.gif?testParam1=testValue1&testParam2=testValue2")).will(returnValue("/contextPath/image.gif?testParam1=testValue1&testParam2=testValue2"));
        mockJspWriter.setExpectedData("/contextPath/image.gif?testParam1=testValue1&testParam2=testValue2");
        mockCtx.expects(atLeastOnce()).method("getMajorVersion").will(returnValue(1));

        ParamTag paramTag = new ParamTag();
        paramTag.setPageContext((PageContext)mockPageCtx.proxy());
        paramTag.setParent(tag);
        paramTag.setName("testParam1");
        paramTag.setValue("'testValue1'");
        ParamTag paramTag2 = new ParamTag();
        paramTag2.setPageContext((PageContext)mockPageCtx.proxy());
        paramTag2.setParent(tag);
        paramTag2.setName("testParam2");
        paramTag2.setValue("'testValue2'");
        tag.setValue("image.gif");
        tag.doStartTag();
        paramTag.doStartTag();
        paramTag.doEndTag();
        paramTag2.doStartTag();
        paramTag2.doEndTag();
        tag.doEndTag();
        mockJspWriter.verify();
    }
