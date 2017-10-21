    @Test
    public void testParentLoad() throws Exception
    {
        _context.setParentLoaderPriority(true);
        
        assertCanLoadClass("org.acme.webapp.ClassInJarA");
        assertCanLoadClass("org.acme.webapp.ClassInJarB");
        assertCanLoadClass("org.acme.other.ClassInClassesC");

        assertCantLoadClass("org.eclipse.jetty.webapp.Configuration");

        Class<?> clazzA = _loader.loadClass("org.acme.webapp.ClassInJarA");
        assertTrue(clazzA.getField("FROM_PARENT")!=null);
    }
