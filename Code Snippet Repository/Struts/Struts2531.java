    private void verify(ServletContext context, String resultPath, Map<String, ResultConfig> results,
                        boolean redirect) {
        assertEquals(4, results.size());
        assertEquals("success", results.get("success").getName());
        assertEquals("input", results.get("input").getName());
        assertEquals("error", results.get("error").getName());
        assertEquals("failure", results.get("failure").getName());
        assertEquals(3, results.get("success").getParams().size());
        assertEquals(resultPath + "/namespace/action-success.jsp", results.get("success").getParams().get("location"));
        assertEquals("value", results.get("success").getParams().get("key"));
        assertEquals("value1", results.get("success").getParams().get("key1"));
        assertEquals(3, results.get("failure").getParams().size());
        assertEquals(resultPath + "/namespace/action-failure.jsp", results.get("failure").getParams().get("location"));
        assertEquals("value", results.get("failure").getParams().get("key"));
        assertEquals("value1", results.get("failure").getParams().get("key1"));

        if (redirect) {
            assertEquals(1, results.get("input").getParams().size());
            assertEquals("foo.action", results.get("input").getParams().get("actionName"));
        } else {
            assertEquals(3, results.get("input").getParams().size());
            assertEquals(resultPath + "/namespace/action.jsp", results.get("input").getParams().get("location"));
            assertEquals("value", results.get("input").getParams().get("key"));
            assertEquals("value1", results.get("input").getParams().get("key1"));
        }

        assertEquals(3, results.get("error").getParams().size());
        assertEquals(resultPath + "/namespace/action.jsp", results.get("error").getParams().get("location"));
        assertEquals("value", results.get("error").getParams().get("key"));
        assertEquals("value1", results.get("error").getParams().get("key1"));
        EasyMock.verify(context);
    }
