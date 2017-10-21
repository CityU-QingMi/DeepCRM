     public void testEscapeCsv() throws Exception {
        // setups
        initDispatcher(new HashMap() {{ put(StrutsConstants.STRUTS_TAG_ALTSYNTAX, "true");}});

        Foo foo = new Foo();
        foo.setTitle("\"something,\",\"");
        stack.push(foo);

        MockJspWriter jspWriter = new MockJspWriter();
        jspWriter.setExpectedData("\"Foo is: \"\"something,\"\",\"\"\"");

        MockPageContext pageContext = new MockPageContext();
        pageContext.setJspWriter(jspWriter);
        pageContext.setRequest(request);

        // test
        {PropertyTag tag = new PropertyTag();
        tag.setEscapeHtml(false);
        tag.setEscapeCsv(true);
        tag.setPageContext(pageContext);
        tag.setValue("%{formatTitle()}");
        tag.doStartTag();
        tag.doEndTag();}

        // verify test
        request.verify();
        jspWriter.verify();
        pageContext.verify();
    }
