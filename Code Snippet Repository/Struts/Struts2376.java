    public void testUsingParamBodyValueToSetConfigurations() throws Exception {

        FormTag formTag = new FormTag();
        formTag.setName("myForm");
        formTag.setPageContext(pageContext);
        formTag.setId("myFormId");
        formTag.setAction("testAction");


        ParamTag formParamTag = new ParamTag();
        formParamTag.setPageContext(pageContext);
        formParamTag.setName("tooltipConfig");
        StrutsMockBodyContent bodyContent = new StrutsMockBodyContent(new MockJspWriter());
        bodyContent.setString(
                "tooltipIcon=/struts/tooltip/myTooltip.gif| " +
                "tooltipDelay=500| " +
                "jsTooltipEnabled=true "
        );
        formParamTag.setBodyContent(bodyContent);

        TextFieldTag tag = new TextFieldTag();
        tag.setPageContext(pageContext);
        tag.setLabel("MyLabel");
        tag.setId("myId");
        tag.setTooltip("myTooltip");


        ParamTag textFieldParamTag = new ParamTag();
        textFieldParamTag.setPageContext(pageContext);
        textFieldParamTag.setName("tooltipConfig");
        StrutsMockBodyContent bodyContent2 = new StrutsMockBodyContent(new MockJspWriter());
        bodyContent2.setString(
                "tooltipIcon=/struts/tooltip/myTooltip2.gif| " +
                "tooltipDelay=5000 "
        );
        textFieldParamTag.setBodyContent(bodyContent2);

        formTag.doStartTag();
        formParamTag.doStartTag();
        formParamTag.doEndTag();
        tag.doStartTag();
        textFieldParamTag.doStartTag();
        textFieldParamTag.doEndTag();
        tag.doEndTag();
        formTag.doEndTag();

        System.out.println(writer.toString());

        verify(TooltipTest.class.getResource("tooltip-3.txt"));
    }
