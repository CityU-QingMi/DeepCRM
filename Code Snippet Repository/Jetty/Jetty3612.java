    @Test
    public void test8859EncodedForm() throws Exception
    {
        _handler._checker = new RequestTester()
        {
            @Override
            public boolean check(HttpServletRequest request,HttpServletResponse response) throws IOException
            {
                // Should be "testä"
                // "test" followed by a LATIN SMALL LETTER A WITH DIAERESIS
                request.setCharacterEncoding(StandardCharsets.ISO_8859_1.name());
                String actual = request.getParameter("name2");
                return "test\u00e4".equals(actual);
            }
        };

        String content="name1=test&name2=test%E4&name3=&name4=test";
        String request="POST / HTTP/1.1\r\n"+
            "Host: whatever\r\n"+
            "Content-Type: "+MimeTypes.Type.FORM_ENCODED.asString()+"\r\n" +
            "Content-Length: "+content.length()+"\r\n"+
            "Connection: close\r\n"+
            "\r\n"+
            content;
        String response = _connector.getResponse(request);
        assertThat(response, containsString(" 200 OK"));
    }
