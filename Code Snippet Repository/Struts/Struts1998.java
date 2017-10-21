    public void testContentTypeIsNotOverwritten() throws Exception {
        servletContext.setRealPath(new File(FreeMarkerResultTest.class.getResource(
                "nested.ftl").toURI()).toURL().getFile());

        FreemarkerResult result = new FreemarkerResult();
        result.setLocation("nested.ftl");
        result.setFreemarkerManager(mgr);

        response.setContentType("contenttype");
        result.execute(invocation);
        assertEquals("contenttype", response.getContentType());
    }
