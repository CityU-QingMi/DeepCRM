    @Test
    public void test9_2_ServerOptions() throws Exception
    {
        // Unsupported in Jetty.
        // Server can handle many webapps, each with their own set of supported OPTIONS.
        // Both www.cnn.com and www.apache.org do NOT support this request as well.

        if (STRICT)
        {
            // Server OPTIONS

            StringBuffer req1 = new StringBuffer();
            req1.append("OPTIONS * HTTP/1.1\n"); // Apply to server in general, rather than a specific resource
            req1.append("Connection: close\n");
            req1.append("Host: localhost\n");
            req1.append("\n");

            HttpTester.Response response = http.request(req1);

            assertEquals("9.2 OPTIONS", HttpStatus.OK_200, response.getStatus());
            assertTrue("9.2 OPTIONS",response.get("Allow") != null);
            // Header expected ...
            // Allow: GET, HEAD, POST, PUT, DELETE, MOVE, OPTIONS, TRACE
            String allow = response.get("Allow");
            String expectedMethods[] =
            { "GET", "HEAD", "POST", "PUT", "DELETE", "MOVE", "OPTIONS", "TRACE" };
            for (String expectedMethod : expectedMethods)
            {
                assertThat(allow,containsString(expectedMethod));
            }
            assertEquals("9.2 OPTIONS","0", response.get("Content-Length")); // Required if no response body.
        }
    }
