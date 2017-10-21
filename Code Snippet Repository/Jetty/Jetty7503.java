     @AfterClass
     public static void tearDown()
         throws Exception
     {
         if (_testServer != null)
         {
             _testServer.stop();
             _testServer = null;
         }
     }
