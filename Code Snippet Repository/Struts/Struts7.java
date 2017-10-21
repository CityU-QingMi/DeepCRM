    public void testPostOrderInJsonWithBadData() throws IOException {
        HttpClient client = new HttpClient();
        PostMethod method = null;
        try {
            method = new PostMethod(ParameterUtils.getBaseUrl()+"/orders.json");
            method.setRequestEntity(new StringRequestEntity("{\"amount\":33}"));
            client.executeMethod(method);
            String response = method.getResponseBodyAsString();
            assertEquals(400, method.getStatusCode());

            assertEquals("{\"actionErrors\":[],\"fieldErrors\":{\"clientName\":[\"The client name is empty\"]}}", response);
            assertNull(method.getResponseHeader("Location"));
        } finally {
            method.releaseConnection();
        }
    }
