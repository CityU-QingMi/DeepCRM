    public void testJSONP() throws Exception {
        JSONResult result = new JSONResult();
        result.setCallbackParameter("callback");
        request.addParameter("callback", "exec");

        executeTest2Action(result);
        String json = response.getContentAsString();

        String normalizedActual = TestUtils.normalize(json, true);
        String normalizedExpected = TestUtils.normalize(JSONResultTest.class.getResource("jsonp-1.txt"));
        assertEquals(normalizedExpected, normalizedActual);
        assertEquals("application/json;charset=UTF-8", response.getContentType());
    }
