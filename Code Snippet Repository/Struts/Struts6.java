    public void testPostOrderInJson() throws IOException {
        HttpClient client = new HttpClient();
        PostMethod method = null;
        try {
            method = new PostMethod(ParameterUtils.getBaseUrl()+"/orders.json");
            method.setRequestEntity(new StringRequestEntity("{\"amount\":33,\"clientName\":\"Test4\"}"));
            client.executeMethod(method);
            assertEquals(201, method.getStatusCode());
            assertTrue(method.getResponseHeader("Location").getValue().startsWith(ParameterUtils.getBaseUrl()+"/orders/"));
        } finally {
            method.releaseConnection();
        }
    }
