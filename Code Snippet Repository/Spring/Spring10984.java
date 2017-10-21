    @Test
    public void fallbackToSystemProperties() {
        MockServletContext servletContext = new MockServletContext();
        System.setProperty("test.prop", "bar");
        try {
            String resolved = ServletContextPropertyUtils.resolvePlaceholders("${test.prop:foo}", servletContext);
            assertEquals(resolved, "bar");
        }
		finally {
            System.clearProperty("test.prop");
        }
    }
