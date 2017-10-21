    @Test
    public void testNormal()
    {
        try (ThreadClassLoaderScope scope = new ThreadClassLoaderScope(new ClassLoaderFoo()))
        {
            assertThat("ClassLoader in scope",Thread.currentThread().getContextClassLoader(),instanceOf(ClassLoaderFoo.class));
            assertThat("Scoped ClassLoader",scope.getScopedClassLoader(),instanceOf(ClassLoaderFoo.class));
        }
        assertThat("ClassLoader after scope",Thread.currentThread().getContextClassLoader(),not(instanceOf(ClassLoaderFoo.class)));
    }
