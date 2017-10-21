    @Test
    public void testFileFromPath() throws Exception
    {
        // Prepare a file to upload.
        String data = "multipart_test_\u20ac";
        Path tmpDir = MavenTestingUtils.getTargetTestingPath();
        Path tmpPath = Files.createTempFile(tmpDir, "multipart_", ".txt");
        Charset encoding = StandardCharsets.UTF_8;
        try (BufferedWriter writer = Files.newBufferedWriter(tmpPath, encoding, StandardOpenOption.CREATE))
        {
            writer.write(data);
        }

        String name = "file";
        String contentType = "text/plain; charset=" + encoding.name();
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
                Assert.assertEquals(tmpPath.getFileName().toString(), part.getSubmittedFileName());
                Assert.assertEquals(Files.size(tmpPath), part.getSize());
                Assert.assertEquals(data, IO.toString(part.getInputStream(), encoding));
            }
        });

        MultiPartContentProvider multiPart = new MultiPartContentProvider();
        PathContentProvider content = new PathContentProvider(contentType, tmpPath);
        content.setByteBufferPool(client.getByteBufferPool());
        multiPart.addFilePart(name, tmpPath.getFileName().toString(), content, null);
        multiPart.close();
        ContentResponse response = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .method(HttpMethod.POST)
                .content(multiPart)
                .send();

        Assert.assertEquals(200, response.getStatus());

        Files.delete(tmpPath);
    }
