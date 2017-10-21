    @Test
    public void test_0_9() throws Exception
    {
        connector.getConnectionFactory(HttpConnectionFactory.class).setHttpCompliance(HttpCompliance.RFC2616);
        LocalEndPoint endp = connector.executeRequest("GET /R1\n");
        endp.waitUntilClosed();
        String response=BufferUtil.toString(endp.takeOutput());

        int offset=0;
        checkNotContained(response,offset,"HTTP/1.1");
        checkNotContained(response,offset,"200");
        checkContains(response,offset,"pathInfo=/R1");
    }
