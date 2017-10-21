    @Test
    public void testGetResourceAsStream_Root() throws Exception
    {
        context.addServlet(ResourceAsStreamServlet.class, "/*");

        StringBuffer req1 = new StringBuffer();
        req1.append("GET /context/ HTTP/1.1\r\n");
        req1.append("Host: local\r\n");
        req1.append("Connection: close\r\n");
        req1.append("\r\n");

        String response = connector.getResponse(req1.toString());
        assertThat("Response", response, containsString("Resource '/': <null>"));
    }
