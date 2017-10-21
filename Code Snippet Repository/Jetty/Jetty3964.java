    @Test
    public void testMultipleDispatchesWithNewQueryStrings() throws Exception 
    {
        String request = 
                "GET /initialCall?initialParam=right HTTP/1.1\r\n" + 
                        "Host: localhost\r\n" + 
                        "Content-Type: application/x-www-form-urlencoded\r\n" +
                        "Connection: close\r\n" + "\r\n";
        String responseString = _connector.getResponse(request);
        assertThat(responseString,startsWith("HTTP/1.1 200"));
    }
