    public void testFormWithActionAndExtension() throws Exception {
        request.setupGetServletPath("/BLA");
        
        FormTag tag = new FormTag();
        tag.setPageContext(pageContext);
        tag.setAction("/testNamespace/testNamespaceAction.jspa");
        tag.setMethod("post");
        tag.setName("myForm");

        tag.doStartTag();
        tag.doEndTag();

        verify(FormTag.class.getResource("Formtag-8.txt"));

    }
