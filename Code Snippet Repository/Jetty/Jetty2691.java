    @Test
    public void testAccess() throws Exception
    {
        StringBuilder request = new StringBuilder();
        request.append("GET ").append(uri).append(" HTTP/1.1\r\n");
        request.append("Host: localhost\r\n");
        request.append("Connection: close\r\n");
        request.append("\r\n");

        String response = connector.getResponse(request.toString());

        switch (expectedStatusCode)
        {
            case 200:
                assertThat(response,startsWith("HTTP/1.1 200 OK"));
                break;
            case 403:
                assertThat(response,startsWith("HTTP/1.1 403 Forbidden"));
                break;
            case 404:
                assertThat(response,startsWith("HTTP/1.1 404 Not Found"));
                break;
            default:
                fail("Write a handler for response status code: " + expectedStatusCode);
                break;
        }

        if (expectedContent != null)
        {
            assertThat(response,containsString("this is open content"));
        }
    }
