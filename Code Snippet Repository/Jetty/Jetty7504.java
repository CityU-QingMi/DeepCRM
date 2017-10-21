     @Test
     public void testPut() throws Exception
     {
         try
         {
             startClient();

             Request request = _client.newRequest(_baseUri.resolve("output.txt"));
             request.method(HttpMethod.PUT);
             request.content(new BytesContentProvider(_content.getBytes()));
             ContentResponse response = request.send();
             int responseStatus = response.getStatus();
             boolean statusOk = (responseStatus == 200 || responseStatus == 201);
             assertTrue(statusOk);
             String content = IO.toString(new FileInputStream(new File(_docRoot,"output.txt")));
             assertEquals(_content,content);
         }
         finally
         {
             stopClient();
         }
     }
