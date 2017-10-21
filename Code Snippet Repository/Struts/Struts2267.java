    public void testFormWithStaticAction() throws Exception {
        request.setupGetServletPath("/");
        request.setupGetContextPath("/");
        request.setRequestURI("/foo.jsp");

        FormTag tag = new FormTag();
        tag.setPageContext(pageContext);
        tag.setAction("test.html");
        tag.doStartTag();
        tag.doEndTag();

        verify(FormTag.class.getResource("Formtag-7.txt"));
    }
