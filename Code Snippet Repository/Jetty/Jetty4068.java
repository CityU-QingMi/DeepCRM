    @Test
    public void testListingWithSession() throws Exception
    {
        ServletHolder defholder = context.addServlet(DefaultServlet.class, "/*");
        defholder.setInitParameter("dirAllowed", "true");
        defholder.setInitParameter("redirectWelcome", "false");
        defholder.setInitParameter("gzip", "false");

        /* create some content in the docroot */
        assertTrue(docRoot.mkdirs());
        assertTrue(new File(docRoot, "one").mkdir());
        assertTrue(new File(docRoot, "two").mkdir());
        assertTrue(new File(docRoot, "three").mkdir());

        StringBuffer req1 = new StringBuffer();
        req1.append("GET /context/;JSESSIONID=1234567890 HTTP/1.0\n\n");

        String response = connector.getResponse(req1.toString());
        
        assertResponseContains("/one/;JSESSIONID=1234567890", response);
        assertResponseContains("/two/;JSESSIONID=1234567890", response);
        assertResponseContains("/three/;JSESSIONID=1234567890", response);

        assertResponseNotContains("<script>", response);
    }
