    @Test
    public void testFileFromInputStream() throws Exception
    {
        String name = "file";
        String fileName = "upload.png";
        String contentType = "image/png";
        byte[] data = new byte[512];
        new Random().nextBytes(data);
        start(new AbstractMultiPartHandler()
        {
            @Override
            protected void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
            {
                Collection<Part> parts = request.getParts();
                Assert.assertEquals(1, parts.size());
                Part part = parts.iterator().next();
                Assert.assertEquals(name, part.getName());
                Assert.assertEquals(contentType, part.getContentType());
                Assert.assertEquals(fileName, part.getSubmittedFileName());
                Assert.assertEquals(data.length, part.getSize());
                Assert.assertArrayEquals(data, IO.readBytes(part.getInputStream()));
            }
        });

        CountDownLatch closeLatch = new CountDownLatch(1);
        MultiPartContentProvider multiPart = new MultiPartContentProvider();
        InputStreamContentProvider content = new InputStreamContentProvider(new ByteArrayInputStream(data)
        {
            @Override
            public void close() throws IOException
            {
                super.close();
                closeLatch.countDown();
            }
        });
        HttpFields fields = new HttpFields();
        fields.put(HttpHeader.CONTENT_TYPE, contentType);
        multiPart.addFilePart(name, fileName, content, fields);
        multiPart.close();
        ContentResponse response = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .method(HttpMethod.POST)
                .content(multiPart)
                .send();

        Assert.assertTrue(closeLatch.await(5, TimeUnit.SECONDS));
        Assert.assertEquals(200, response.getStatus());
    }
