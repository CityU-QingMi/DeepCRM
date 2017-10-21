    @Test
    public void testFormContentProviderWithDifferentContentType() throws Exception
    {
        final String name1 = "a";
        final String value1 = "1";
        final String name2 = "b";
        final String value2 = "2";
        Fields fields = new Fields();
        fields.put(name1, value1);
        fields.add(name2, value2);
        final String content = FormContentProvider.convert(fields);
        final String contentType = "text/plain;charset=UTF-8";

        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                Assert.assertEquals("POST", request.getMethod());
                Assert.assertEquals(contentType, request.getContentType());
                Assert.assertEquals(content, IO.toString(request.getInputStream()));
            }
        });

        ContentResponse response = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .method(HttpMethod.POST)
                .content(new FormContentProvider(fields))
                .header(HttpHeader.CONTENT_TYPE, contentType)
                .send();

        Assert.assertEquals(200, response.getStatus());
    }
