    @Test
    public void testFormContentProvider() throws Exception
    {
        final String name1 = "a";
        final String value1 = "1";
        final String name2 = "b";
        final String value2 = "2";
        final String value3 = "\u20AC";

        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                Assert.assertEquals("POST", request.getMethod());
                Assert.assertEquals(MimeTypes.Type.FORM_ENCODED.asString(), request.getContentType());
                Assert.assertEquals(value1, request.getParameter(name1));
                String[] values = request.getParameterValues(name2);
                Assert.assertNotNull(values);
                Assert.assertEquals(2, values.length);
                Assert.assertThat(values, Matchers.arrayContainingInAnyOrder(value2, value3));
            }
        });

        Fields fields = new Fields();
        fields.put(name1, value1);
        fields.add(name2, value2);
        fields.add(name2, value3);
        ContentResponse response = client.FORM(scheme + "://localhost:" + connector.getLocalPort(), fields);

        Assert.assertEquals(200, response.getStatus());
    }
