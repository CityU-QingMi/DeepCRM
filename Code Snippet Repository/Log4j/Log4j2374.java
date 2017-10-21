    @Test
    public void testMultipleClassLoaders() throws Exception {
        final Class<?> logging1 = loader1.loadClass(PKG + ".a.Logging1");
        final Field field1 = logging1.getDeclaredField("logger");
        final Logger logger1 = (Logger) ReflectionUtil.getStaticFieldValue(field1);
        assertNotNull(logger1);
        final Class<?> logging2 = loader2.loadClass(PKG + ".b.Logging2");
        final Field field2 = logging2.getDeclaredField("logger");
        final Logger logger2 = (Logger) ReflectionUtil.getStaticFieldValue(field2);
        assertNotNull(logger2);
        final Class<?> logging3 = loader3.loadClass(PKG + ".c.Logging3");
        final Field field3 = logging3.getDeclaredField("logger");
        final Logger logger3 = (Logger) ReflectionUtil.getStaticFieldValue(field3);
        assertNotNull(logger3);
        assertNotSame(logger1.getContext(), logger2.getContext());
        assertNotSame(logger1.getContext(), logger3.getContext());
        assertNotSame(logger2.getContext(), logger3.getContext());
    }
