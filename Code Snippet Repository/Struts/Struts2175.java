    public void testParametersPriorityWithIncludeParamsAsNONE() throws Exception {
        request.setQueryString("id1=urlId1&id2=urlId2&urlParam1=urlValue1&urlParam2=urlValue2");

        tag.setValue("testAction.action?id1=tagId1&id2=tagId2&tagId=tagValue");
        tag.setIncludeParams("NONE");

        ParamTag param1 = new ParamTag();
        param1.setPageContext(pageContext);
        param1.setName("param1");
        param1.setValue("%{'param1value'}");

        ParamTag param2 = new ParamTag();
        param2.setPageContext(pageContext);
        param2.setName("param2");
        param2.setValue("%{'param2value'}");

        ParamTag param3 = new ParamTag();
        param3.setPageContext(pageContext);
        param3.setName("id1");
        param3.setValue("%{'paramId1'}");


        tag.doStartTag();
        param1.doStartTag();
        param1.doEndTag();
        param2.doStartTag();
        param2.doEndTag();
        param3.doStartTag();
        param3.doEndTag();

        URL url = (URL) tag.getComponent();
        Map parameters = url.getParameters();

        assertEquals(parameters.size(), 5);
        assertEquals(parameters.get("id1"), "paramId1");
        assertEquals(parameters.get("id2"), "tagId2");
        assertEquals(parameters.get("tagId"), "tagValue");
        assertEquals(parameters.get("param1"), "param1value");
        assertEquals(parameters.get("param2"), "param2value");
    }
