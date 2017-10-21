    public void testContentTypeFromTemplate() throws Exception {
        servletContext.setRealPath(new File(FreeMarkerResultTest.class.getResource(
                "something.ftl").toURI()).toURL().getFile());

        FreemarkerResult result = new FreemarkerResult();
        result.setLocation("something.ftl");
        result.setFreemarkerManager(mgr);

        assertNull(response.getContentType());
        result.execute(invocation);
        assertEquals("text/xml", response.getContentType());
    }
