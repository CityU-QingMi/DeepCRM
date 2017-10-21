    public void testBuildParametersStringWithUrlHavingSomeExistingParameters() throws Exception {
        String expectedUrl = "http://localhost:8080/myContext/myPage.jsp?initParam=initValue&amp;param1=value1&amp;param2=value2&amp;param3%22%3CsCrIpT%3Ealert%281%29%3B%3C%2FsCrIpT%3E=value3";

        Map parameters = new LinkedHashMap();
        parameters.put("param1", "value1");
        parameters.put("param2", "value2");
        parameters.put("param3\"<sCrIpT>alert(1);</sCrIpT>","value3");

        StringBuilder url = new StringBuilder("http://localhost:8080/myContext/myPage.jsp?initParam=initValue");

        urlHelper.buildParametersString(parameters, url, UrlHelper.AMP);

        assertEquals(
           expectedUrl, url.toString());
    }
