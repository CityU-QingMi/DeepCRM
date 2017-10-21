    public void testPostOrderInXml() throws IOException {
        HttpClient client = new HttpClient();
        PostMethod method = null;
        try {
            method = new PostMethod(ParameterUtils.getBaseUrl()+"/orders.xml");
            method.setRequestEntity(new StringRequestEntity("<org.apache.struts2.rest.example.Order>\n" +
                    "<clientName>Test3</clientName>\n" +
                    "<amount>3342</amount>\n" +
                    "</org.apache.struts2.rest.example.Order>"));
            client.executeMethod(method);
            assertEquals(201, method.getStatusCode());
            assertTrue(method.getResponseHeader("Location").getValue().startsWith(ParameterUtils.getBaseUrl()+"/orders/"));
        } finally {
            method.releaseConnection();
        }
    }
