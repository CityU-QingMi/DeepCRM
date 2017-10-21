    public void testIncludeParamsDefaultToGET() throws Exception {
        request.setQueryString("one=oneVal&two=twoVal&three=threeVal");

        // request parameter map should not have any effect, as includeParams
        // default to GET, which get its param from request.getQueryString()
        Map tmp = new HashMap();
        tmp.put("one", "aaa");
        tmp.put("two", "bbb");
        tmp.put("three", "ccc");
        request.setParameterMap(tmp);

        tag.setValue("TestAction.acton");

        tag.doStartTag();

        URL url = (URL) tag.getComponent();
        Map parameters = url.getParameters();

        tag.doEndTag();

        assertEquals(parameters.get("one"), "oneVal");
        assertEquals(parameters.get("two"), "twoVal");
        assertEquals(parameters.get("three"), "threeVal");
    }
