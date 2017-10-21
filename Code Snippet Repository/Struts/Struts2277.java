    public void testFormWithActionAttributeContainingBothActionAndMethodAndNamespace() throws Exception {
        FormTag tag = new FormTag();
        tag.setPageContext(pageContext);
        tag.setName("myForm");
        tag.setNamespace("/testNamespace");
        tag.setMethod("post");
        tag.setAcceptcharset("UTF-8");
        tag.setAction("testNamespaceAction");
        tag.setEnctype("myEncType");
        tag.setTitle("mytitle");
        tag.setOnsubmit("submitMe()");

        tag.doStartTag();
        tag.doEndTag();

        verify(FormTag.class.getResource("Formtag-10.txt"));
    }
