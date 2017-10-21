     @Test
     public void testPost() throws Exception
     {
         try
         {
             startClient();

             Request request = _client.newRequest(_baseUri.resolve("test"));
             request.method(HttpMethod.POST);
             request.content(new BytesContentProvider(_content.getBytes()));
             ContentResponse response = request.send();
             assertEquals(HttpStatus.OK_200,response.getStatus());
             assertEquals(_content,_testServer.getTestHandler().getRequestContent());
         }
         finally
         {
             stopClient();
         }
     }
