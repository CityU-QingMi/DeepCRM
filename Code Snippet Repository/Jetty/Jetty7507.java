     @Ignore
     public void testHead() throws Exception
     {
         try
         {
             startClient();

             Request request = _client.newRequest(_baseUri.resolve("input.txt"));
             request.method(HttpMethod.HEAD);
             ContentResponse response = request.send();
             int responseStatus = response.getStatus();
             assertEquals(HttpStatus.OK_200,responseStatus);
         }
         finally
         {
             stopClient();
         }
     }
