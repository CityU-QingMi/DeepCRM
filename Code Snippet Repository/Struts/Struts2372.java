    public void testWithFormOverridingNew() throws Exception {

        FormTag formTag = new FormTag();
        formTag.setPageContext(pageContext);
        formTag.setName("myForm");
        formTag.setId("myFormId");
        formTag.setAction("testAction");

        // same parameters as the OGNL map configuration, output must be the same
        formTag.setTooltip("myTooltip");
        formTag.setTooltipIconPath("/struts/tooltip/myTooltip.gif");
        formTag.setTooltipDelay("500");
        formTag.setJavascriptTooltip("true");


        TextFieldTag tag = new TextFieldTag();
        tag.setPageContext(pageContext);
        tag.setLabel("MyLabel");
        tag.setId("myId");

        tag.setTooltip("myTooltip");

        formTag.doStartTag();
        tag.doStartTag();
        tag.doEndTag();
        formTag.doEndTag();

        verify(TooltipTest.class.getResource("tooltip-2.txt"));
    }
