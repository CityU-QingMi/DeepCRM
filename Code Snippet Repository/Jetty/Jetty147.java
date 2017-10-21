    @Test
    public void testResourcesAnnotation ()
    throws Exception
    {
        new org.eclipse.jetty.plus.jndi.EnvEntry(server, "resA", objA, false);
        new org.eclipse.jetty.plus.jndi.EnvEntry(server, "resB", objB, false);

        AnnotationIntrospector introspector = new AnnotationIntrospector();
        ResourcesAnnotationHandler handler = new ResourcesAnnotationHandler(wac);
        introspector.registerHandler(handler);
        introspector.introspect(ResourceA.class);
        introspector.introspect(ResourceB.class);

        assertEquals(objA, env.lookup("peach"));
        assertEquals(objB, env.lookup("pear"));
    }
