    public void testBuildResultWithParameter() throws Exception {

        ResultConfig resultConfig = new ResultConfig.Builder("", ServletActionRedirectResult.class.getName())
            .addParam("actionName", "someActionName")
            .addParam("namespace", "someNamespace")
            .addParam("encode", "true")
            .addParam("parse", "true")
            .addParam("location", "someLocation")
            .addParam("prependServletContext", "true")
            .addParam("method", "someMethod")
            .addParam("param1", "value 1")
            .addParam("param2", "value 2")
            .addParam("param3", "value 3")
            .addParam("anchor", "fragment")
            .build();

        ObjectFactory factory = container.getInstance(ObjectFactory.class);
        ServletActionRedirectResult result = (ServletActionRedirectResult) factory.buildResult(resultConfig, new HashMap<String, Object>());
        assertNotNull(result);
    }
