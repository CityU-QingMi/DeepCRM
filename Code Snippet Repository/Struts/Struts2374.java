    public void testWithPartialFormOverridingNew() throws Exception {

        FormTag formTag = new FormTag();
        formTag.setName("myForm");
        formTag.setPageContext(pageContext);
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


        //same parameters as the OGNL map configuration, output must be the same
        tag.setTooltip("myTooltip");
        tag.setTooltipIconPath("/struts/tooltip/myTooltip2.gif");
        tag.setTooltipDelay("5000");
        tag.setJavascriptTooltip("true");

        formTag.doStartTag();
        tag.doStartTag();
        tag.doEndTag();
        formTag.doEndTag();

        verify(TooltipTest.class.getResource("tooltip-3.txt"));
    }
