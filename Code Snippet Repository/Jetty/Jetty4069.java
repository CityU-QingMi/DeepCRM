    @Test
    public void testListingXSS() throws Exception
    {
        ServletHolder defholder = context.addServlet(DefaultServlet.class, "/*");
        defholder.setInitParameter("dirAllowed", "true");
        defholder.setInitParameter("redirectWelcome", "false");
        defholder.setInitParameter("gzip", "false");

        /* create some content in the docroot */
        FS.ensureDirExists(docRoot);
        assertTrue(new File(docRoot, "one").mkdir());
        assertTrue(new File(docRoot, "two").mkdir());
        assertTrue(new File(docRoot, "three").mkdir());
        if (!OS.IS_WINDOWS)
        {
            assertTrue("Creating dir 'f??r' (Might not work in Windows)", new File(docRoot, "f??r").mkdir());
        }

        StringBuffer req1 = new StringBuffer();
/**/
/**/
/**/
/**/
        req1.append("GET /context/;<script>window.alert(\"hi\");</script> HTTP/1.0\n");
        req1.append("\n");

        String response = connector.getResponse(req1.toString());

        assertResponseNotContains("<script>", response);
    }
