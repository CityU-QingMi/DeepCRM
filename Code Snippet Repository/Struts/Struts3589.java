    public void testResourceUrl() throws Exception {
        mockHttpReq.stubs().method("getQueryString").will(returnValue(""));
        mockPortletRes.expects(once()).method("encodeURL").will(returnValue("/contextPath/image.gif"));
        mockJspWriter.setExpectedData("/contextPath/image.gif");
        mockCtx.expects(atLeastOnce()).method("getMajorVersion").will(returnValue(1));
        tag.setValue("image.gif");
        tag.doStartTag();
        tag.doEndTag();
        mockJspWriter.verify();
    }
