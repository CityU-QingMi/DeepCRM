    public void testFormWithActionAttributeContainingBothActionAndMethod() throws Exception {
        FormTag tag = new FormTag();
        tag.setPageContext(pageContext);
        tag.setName("myForm");
        tag.setMethod("post");
        tag.setAcceptcharset("UTF-8");
        tag.setAction("testAction");
        tag.setEnctype("myEncType");
        tag.setTitle("mytitle");
        tag.setOnsubmit("submitMe()");
        tag.doStartTag();
        tag.doEndTag();

        verify(FormTag.class.getResource("Formtag-9.txt"));
    }
