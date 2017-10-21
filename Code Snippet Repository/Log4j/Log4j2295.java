    public DatePatternConverterTest(final Boolean threadLocalEnabled) throws Exception {
        // Setting the system property does not work: the Constant field has already been initialized...
        //System.setProperty("log4j2.enable.threadlocals", threadLocalEnabled.toString());

        final Field field = Constants.class.getDeclaredField("ENABLE_THREADLOCALS");
        field.setAccessible(true); // make non-private

        final Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL); // make non-final

        field.setBoolean(null, threadLocalEnabled.booleanValue());
    }
