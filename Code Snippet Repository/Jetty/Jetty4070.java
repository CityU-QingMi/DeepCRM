    @Test
    public void testListingProperUrlEncoding() throws Exception
    {
        ServletHolder defholder = context.addServlet(DefaultServlet.class, "/*");
        defholder.setInitParameter("dirAllowed", "true");
        defholder.setInitParameter("redirectWelcome", "false");
        defholder.setInitParameter("gzip", "false");

        /* create some content in the docroot */
        
        assertTrue(docRoot.mkdirs());
        File wackyDir = new File(docRoot, "dir;"); // this should not be double-encoded.
        assertTrue(wackyDir.mkdirs());

        assertTrue(new File(wackyDir, "four").mkdir());
        assertTrue(new File(wackyDir, "five").mkdir());
        assertTrue(new File(wackyDir, "six").mkdir());

/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/


        // First send request in improper, unencoded way.
        String response = connector.getResponse("GET /context/dir;/ HTTP/1.0\r\n\r\n");

        assertResponseContains("HTTP/1.1 404 Not Found", response);

        // Now send request in proper, encoded format.
        response = connector.getResponse("GET /context/dir%3B/ HTTP/1.0\r\n\r\n");

        // Should not see double-encoded ";"
        // First encoding: ";" -> "%3b"
        // Second encoding: "%3B" -> "%253B" (BAD!)
        assertResponseNotContains("%253B", response);

        assertResponseContains("/dir%3B/", response);
        assertResponseContains("/dir%3B/four/", response);
        assertResponseContains("/dir%3B/five/", response);
        assertResponseContains("/dir%3B/six/", response);
    }
