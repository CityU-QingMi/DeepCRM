     @Test
     public void testGet() throws Exception
     {
         try
         {
             startClient();

             ContentResponse response = _client.GET(_baseUri.resolve("input.txt"));
             assertEquals(HttpServletResponse.SC_OK,response.getStatus());
             assertEquals(_content, response.getContentAsString());
         }
         finally
         {
             stopClient();
         }
     }
