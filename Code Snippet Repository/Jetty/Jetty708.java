    @Test
    public void testFieldWithOverridenContentType() throws Exception
    {
        String name = "field";
        String value = "\u00e8";
        Charset encoding = StandardCharsets.ISO_8859_1;
        start(new AbstractMultiPartHandler()
        {
            @Override
            protected void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
            {
                Collection<Part> parts = request.getParts();
                Assert.assertEquals(1, parts.size());
                Part part = parts.iterator().next();
                Assert.assertEquals(name, part.getName());
                String contentType = part.getContentType();
                Assert.assertNotNull(contentType);
                int equal = contentType.lastIndexOf('=');
                Charset charset = Charset.forName(contentType.substring(equal + 1));
                Assert.assertEquals(encoding, charset);
                Assert.assertEquals(value, IO.toString(part.getInputStream(), charset));
            }
        });

        MultiPartContentProvider multiPart = new MultiPartContentProvider();
        HttpFields fields = new HttpFields();
        fields.put(HttpHeader.CONTENT_TYPE, "text/plain;charset=" + encoding.name());
        BytesContentProvider content = new BytesContentProvider(value.getBytes(encoding));
        multiPart.addFieldPart(name, content, fields);
        multiPart.close();
        ContentResponse response = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .method(HttpMethod.POST)
                .content(multiPart)
                .send();

        Assert.assertEquals(200, response.getStatus());
    }
