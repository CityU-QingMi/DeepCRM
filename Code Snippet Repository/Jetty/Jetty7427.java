    @Test
    public void test9_2_ResourceOptions() throws Exception
    {
        // Jetty is conditionally compliant.
        // Possible Bug in the Spec.
        // The content-length: 0 in the spec is not appropriate if the connection is being closed.

        // Resource specific OPTIONS
        StringBuffer req2 = new StringBuffer();
        req2.append("OPTIONS /rfc2616-webapp/httpmethods HTTP/1.1\n"); // Apply to specific resource
        req2.append("Host: localhost\n");
        req2.append("\n");

        // Test issues 2 requests. first as OPTIONS (not closed),
        // second as GET (closed), this is to allow the 2 conflicting aspects of the
        // RFC2616 rules with regards to section 9.2 (OPTIONS) and section 4.4 (Message Length)
        // to not conflict with each other.

        req2.append("GET /rfc2616-webapp/httpmethods HTTP/1.1\n");
        req2.append("Host: localhost\n");
        req2.append("Connection: close\n"); // Close this second request
        req2.append("\n");

        List<HttpTester.Response> responses = http.requests(req2);

        Assert.assertEquals("Response Count",2,responses.size()); // Should have 2 responses

        HttpTester.Response response = responses.get(0); // Only interested in first response
        assertTrue("9.2 OPTIONS", response.get("Allow") != null);
        // Header expected ...
        // Allow: GET, HEAD, POST, TRACE, OPTIONS
        String allow = response.get("Allow");
        String expectedMethods[] =
        { "GET", "HEAD", "POST", "OPTIONS", "TRACE" };
        for (String expectedMethod : expectedMethods)
        {
            assertThat(allow,containsString(expectedMethod));
        }

        assertEquals("9.2 OPTIONS","0", response.get("Content-Length")); // Required if no response body.
    }
