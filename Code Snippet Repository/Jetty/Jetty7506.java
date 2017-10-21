     @Test
     public void testGetNonExistantUser () throws Exception
     {
         try
         {
             startClient("foo", "bar");
             ContentResponse response = _client.GET(_baseUri.resolve("input.txt"));
             assertEquals(HttpServletResponse.SC_UNAUTHORIZED,response.getStatus());
         }
         finally
         {
             stopClient();
         }
     }
