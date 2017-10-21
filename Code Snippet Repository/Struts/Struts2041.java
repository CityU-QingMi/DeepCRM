    public void testRequestURIActionIncludeAll() throws Exception {
        request.setRequestURI("/public/about");
        request.setQueryString("section=team&company=acme inc");

        tag.setAction("team");
        tag.setIncludeParams("all");

        tag.doStartTag();

        // include nested param tag
        ParamTag paramTag = new ParamTag();
        paramTag.setPageContext(pageContext);
        paramTag.setName("year");
        paramTag.setValue("2006");
        paramTag.doStartTag();
        paramTag.doEndTag();

        tag.doEndTag();

        String result = writer.toString();
        assertTrue(result.contains("/team.action?"));
        assertTrue(result.contains("section=team"));
        assertTrue(result.contains("company=acme+inc"));
        assertTrue(result.contains("year=2006"));
    }
