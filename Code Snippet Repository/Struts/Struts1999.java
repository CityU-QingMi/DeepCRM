    public void testDefaultContentType() throws Exception {
        servletContext.setRealPath(new File(FreeMarkerResultTest.class.getResource(
                "nested.ftl").toURI()).toURL().getFile());

        FreemarkerResult result = new FreemarkerResult();
        result.setLocation("nested.ftl");
        result.setFreemarkerManager(mgr);

        assertNull(response.getContentType());
        result.execute(invocation);
        assertEquals("text/html; charset=UTF-8", response.getContentType());
    }
