    public void testFormWithActionAttributeContainingBothActionAndDMIMethod() throws Exception {
        FormTag tag = new FormTag();
        tag.setPageContext(pageContext);
        tag.setName("myForm");
        tag.setMethod("post");
        tag.setAcceptcharset("UTF-8");
        tag.setAction("testAction!testMethod");
        tag.setEnctype("myEncType");
        tag.setTitle("mytitle");
        tag.setOnsubmit("submitMe()");

        ((DefaultActionMapper)container.getInstance(ActionMapper.class)).setAllowDynamicMethodCalls("true");

        tag.doStartTag();
        tag.doEndTag();

        verify(FormTag.class.getResource("Formtag-23.txt"));
    }    
