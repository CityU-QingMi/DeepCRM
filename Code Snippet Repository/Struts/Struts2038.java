    public void testRequestURIActionIncludeGetDoNotEscapeAmp() throws Exception {
        request.setRequestURI("/public/about");
        request.setQueryString("section=team&company=acme inc");

        tag.setAction("team");
        tag.setIncludeParams("get");
        tag.setEscapeAmp("false");
        tag.doStartTag();
        tag.doEndTag();

        assertEquals(wrapWithAnchor("/team.action?section=team&company=acme+inc"), writer.toString());
    }
