     @Test
     public void testEmpty()
     throws Exception
     {
         String delimiter = "\r\n";
         final String boundary = "MockMultiPartTestBoundary";

         String str =
                 delimiter +
                 "--" + boundary + "--" + delimiter; 

         MultipartConfigElement config = new MultipartConfigElement(_dirname, 1024, 3072, 50);
         MultiPartInputStreamParser mpis = new MultiPartInputStreamParser(new ByteArrayInputStream(str.getBytes()),
                                                                          "multipart/form-data, boundary="+boundary,
                                                                          config,
                                                                          _tmpDir);
         mpis.setDeleteOnExit(true);
         assertTrue(mpis.getParts().isEmpty());
     }
