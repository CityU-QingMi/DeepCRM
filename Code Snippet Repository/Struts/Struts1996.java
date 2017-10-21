    public void testWriteIfCompleted() throws Exception {
        FreemarkerResult result = new FreemarkerResult();
        result.setLocation("someFreeMarkerFile.ftl");
        result.setFreemarkerManager(mgr);
        result.setWriteIfCompleted(true);

        try {
            result.execute(invocation);
            assertTrue(false);
        } catch (Exception e) {
            assertEquals(0, stringWriter.getBuffer().length());
        }
    }
