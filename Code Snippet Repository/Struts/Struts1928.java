    public void testPlainTextWithoutSlash() throws Exception {
        PlainTextResult result = new PlainTextResult();
        result.setLocation("someJspFile.jsp");

        response.setExpectedContentType("text/plain");
        response.setExpectedHeader("Content-Disposition", "inline");

        try (InputStream jspResourceInputStream =
            ClassLoaderUtil.getResourceAsStream("org/apache/struts2/dispatcher/someJspFile.jsp", PlainTextResultTest.class)) {
            servletContext.setResourceAsStream(jspResourceInputStream);
            result.execute(invocation);

            String r = AbstractUITagTest.normalize(stringWriter.getBuffer().toString(), true);
            String e = AbstractUITagTest.normalize(readAsString("org/apache/struts2/dispatcher/someJspFile.jsp"), true);
            assertEquals(r, e);
        }
    }
