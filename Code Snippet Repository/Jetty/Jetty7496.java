     @Test
     public void testGetAndPasswordUpdate() throws Exception
     {
         try
         {
             startClient("jetty", "jetty");

             ContentResponse response = _client.GET(_baseUri.resolve("input.txt"));
             assertEquals(HttpServletResponse.SC_OK,response.getStatus());
             assertEquals(_content, response.getContentAsString());
             
             stopClient();
             
             String newpwd = String.valueOf(System.currentTimeMillis());
             
             changePassword("jetty", newpwd);
           
             
             startClient("jetty", newpwd);
             
             response = _client.GET(_baseUri.resolve("input.txt"));
             assertEquals(HttpServletResponse.SC_OK,response.getStatus());
             assertEquals(_content, response.getContentAsString());
             
         }
         finally
         {
             stopClient();
         }
     }
