    @Test
    public void test14_16_NoRange() throws Exception
    {
        //
        // calibrate with normal request (no ranges); if this doesnt
        // work, dont expect ranges to work either
        //

        StringBuffer req1 = new StringBuffer();
        req1.append("GET /rfc2616-webapp/alpha.txt HTTP/1.1\n");
        req1.append("Host: localhost\n");
        req1.append("Connection: close\n");
        req1.append("\n");

        HttpTester.Response response = http.request(req1);

        assertEquals(HttpStatus.OK_200, response.getStatus());
        assertTrue(response.getContent().contains(ALPHA));
    }
