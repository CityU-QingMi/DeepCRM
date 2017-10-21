    @Test
    public void testWithException()
    {
        try (ThreadClassLoaderScope scope = new ThreadClassLoaderScope(new ClassLoaderBar()))
        {
            assertThat("ClassLoader in 'scope'",Thread.currentThread().getContextClassLoader(),instanceOf(ClassLoaderBar.class));
            assertThat("Scoped ClassLoader",scope.getScopedClassLoader(),instanceOf(ClassLoaderBar.class));
            try (ThreadClassLoaderScope inner = new ThreadClassLoaderScope(new ClassLoaderFoo()))
            {
                assertThat("ClassLoader in 'inner'",Thread.currentThread().getContextClassLoader(),instanceOf(ClassLoaderFoo.class));
                assertThat("Scoped ClassLoader",scope.getScopedClassLoader(),instanceOf(ClassLoaderFoo.class));
                throw new RuntimeException("Intention exception");
            }
        }
        catch (Throwable ignore)
        {
            /* ignore */
        }
        assertThat("ClassLoader after 'scope'",Thread.currentThread().getContextClassLoader(),not(instanceOf(ClassLoaderBar.class)));
    }
