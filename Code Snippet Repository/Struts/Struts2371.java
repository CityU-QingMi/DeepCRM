    public void testWithFormOverriding() throws Exception {

        FormTag formTag = new FormTag();
        formTag.setPageContext(pageContext);
        formTag.setName("myForm");
        formTag.setId("myFormId");
        formTag.setAction("testAction");

        formTag.setTooltipConfig(
                "#{" +
                "'tooltipIcon':'/struts/tooltip/myTooltip.gif', " +
                "'tooltipDelay':'500', " +
                "'jsTooltipEnabled':'true' "+
                "}"
        );


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
