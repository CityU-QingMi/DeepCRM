    public void testBuildParametersStringWithJavaScriptInjected() throws Exception {
        String expectedUrl = "http://localhost:8080/myContext/myPage.jsp?initParam=initValue&amp;param1=value1&amp;param2=value2&amp;param3%22%3Cscript+type%3D%22text%2Fjavascript%22%3Ealert%281%29%3B%3C%2Fscript%3E=value3";

        Map parameters = new LinkedHashMap();
        parameters.put("param1", "value1");
        parameters.put("param2", "value2");
        parameters.put("param3\"<script type=\"text/javascript\">alert(1);</script>","value3");

        StringBuilder url = new StringBuilder("http://localhost:8080/myContext/myPage.jsp?initParam=initValue");

        urlHelper.buildParametersString(parameters, url, UrlHelper.AMP);

        assertEquals(
           expectedUrl, url.toString());
    }
