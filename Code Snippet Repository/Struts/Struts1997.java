    public void testWithoutWriteIfCompleted() throws Exception {
        FreemarkerResult result = new FreemarkerResult();
        result.setLocation("someFreeMarkerFile.ftl");
        result.setFreemarkerManager(mgr);

        try {
            result.execute(invocation);
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(stringWriter.getBuffer().length() > 0);
        }
    }
