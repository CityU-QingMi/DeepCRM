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
        tag.doEndTag();

        assertEquals(wrapWithAnchor("testAction.action?id1=paramId1&amp;id2=tagId2&amp;tagId=tagValue&amp;param1=param1value&amp;param2=param2value"), writer.toString());
    }
