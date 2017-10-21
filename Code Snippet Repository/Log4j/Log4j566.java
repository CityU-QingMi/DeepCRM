    @Ignore
    @AfterClass
    public static void afterClass() throws Exception {
        System.clearProperty("log4j.unbox.ringbuffer.size");

        // ensure subsequent tests (which assume 32 slots) pass
        final Field field = Unbox.class.getDeclaredField("RINGBUFFER_SIZE");
        field.setAccessible(true); // make non-private

        final Field modifierField = Field.class.getDeclaredField("modifiers");
        modifierField.setAccessible(true);
        modifierField.setInt(field, field.getModifiers() &~ Modifier.FINAL); // make non-final

        field.set(null, 32); // reset to default

        final Field threadLocalField = Unbox.class.getDeclaredField("threadLocalState");
        threadLocalField.setAccessible(true);
        final ThreadLocal<?> threadLocal = (ThreadLocal<?>) threadLocalField.get(null);
        threadLocal.remove();
        threadLocalField.set(null, new ThreadLocal<>());
    }
