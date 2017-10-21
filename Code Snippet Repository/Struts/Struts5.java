    public void testPostOrderInXmlWithBadData() throws IOException {
        HttpClient client = new HttpClient();
        PostMethod method = null;
        try {
            method = new PostMethod(ParameterUtils.getBaseUrl()+"/orders.xml");
            method.setRequestEntity(new StringRequestEntity("<org.apache.struts2.rest.example.Order>\n" +
                    "<amount>3342</amount>\n" +
                    "</org.apache.struts2.rest.example.Order>"));
            client.executeMethod(method);
            assertEquals(400, method.getStatusCode());
            String response = method.getResponseBodyAsString();
            assertTrue(response.contains("<string>The client name is empty"));
            assertNull(method.getResponseHeader("Location"));
        } finally {
            method.releaseConnection();
        }
    }
