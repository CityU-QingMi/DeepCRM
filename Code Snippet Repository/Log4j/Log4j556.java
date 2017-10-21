    @Test
    public void testStackTraceEquivalence() throws Exception {
        for (int i = 1; i < 15; i++) {
            final Class<?> expected = Reflection.getCallerClass(i + StackLocator.JDK_7u25_OFFSET);
            final Class<?> actual = StackLocatorUtil.getCallerClass(i);
            final Class<?> fallbackActual = Class.forName(
                StackLocatorUtil.getStackTraceElement(i).getClassName());
            assertSame(expected, actual);
            assertSame(expected, fallbackActual);
        }
    }
