    public void testFormWithCustomOnsubmitEnabledWithValidateEnabled2() throws Exception {
        prepareMockInvocation();

        FormTag tag = new FormTag();
        tag.setPageContext(pageContext);
        tag.setName("myForm");
        tag.setMethod("post");
        tag.setAction("testAction");
        tag.setAcceptcharset("UTF-8");
        tag.setEnctype("myEncType");
        tag.setTitle("mytitle");
        tag.setOnsubmit("submitMe()");
        tag.setValidate("true");
        tag.setNamespace("");

        UpDownSelectTag t = new UpDownSelectTag();
        t.setPageContext(pageContext);
        t.setName("myUpDownSelectTag");
        t.setList("{}");

        tag.doStartTag();
        t.doStartTag();
        t.doEndTag();
        tag.doEndTag();

        verify(FormTag.class.getResource("Formtag-11.txt"));
    }
