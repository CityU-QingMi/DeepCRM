    public void testWithPartialFormOverriding() throws Exception {

        FormTag formTag = new FormTag();
        formTag.setName("myForm");
        formTag.setPageContext(pageContext);
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
        tag.setTooltipConfig(
                "#{" +
                "'tooltipIcon':'/struts/tooltip/myTooltip2.gif', " +
                "'tooltipDelay':'5000' " +
                "}"
        );

        formTag.doStartTag();
        tag.doStartTag();
        tag.doEndTag();
        formTag.doEndTag();

        verify(TooltipTest.class.getResource("tooltip-3.txt"));
    }
