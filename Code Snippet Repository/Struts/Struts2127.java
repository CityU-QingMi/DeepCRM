    public void testWithoutAltSyntax1() throws Exception {
        //      setups
        initDispatcher(new HashMap() {{ put(StrutsConstants.STRUTS_TAG_ALTSYNTAX, "false");}});

        Foo foo = new Foo();
        foo.setTitle("tm_jee");
        stack.push(foo);

        MockJspWriter jspWriter = new MockJspWriter();
        jspWriter.setExpectedData("Foo is: tm_jee");

        MockPageContext pageContext = new MockPageContext();
        pageContext.setJspWriter(jspWriter);
        pageContext.setRequest(request);

        // test
        {PropertyTag tag = new PropertyTag();
        tag.setPageContext(pageContext);
        tag.setValue("formatTitle()");
        tag.doStartTag();
        tag.doEndTag();}

        // verify test
        request.verify();
        jspWriter.verify();
        pageContext.verify();
    }
