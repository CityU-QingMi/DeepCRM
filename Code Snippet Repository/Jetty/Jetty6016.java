    @Test
    public void testWebAppLoad() throws Exception
    {
        _context.setParentLoaderPriority(false);
        assertCanLoadClass("org.acme.webapp.ClassInJarA");
        assertCanLoadClass("org.acme.webapp.ClassInJarB");
        assertCanLoadClass("org.acme.other.ClassInClassesC");

        assertCantLoadClass("org.eclipse.jetty.webapp.Configuration");

        Class<?> clazzA = _loader.loadClass("org.acme.webapp.ClassInJarA");
        expectedException.expect(NoSuchFieldException.class);
        clazzA.getField("FROM_PARENT");
    }
