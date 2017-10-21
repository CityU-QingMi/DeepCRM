    @Test
    public void testCustomLevelSerialization() throws Exception {
        final CustomLevel custom = new CustomLevel();
        final Object obj = SerializationTestHelper.serializeClone(custom);
        assertTrue(obj instanceof CustomLevel);

        final CustomLevel clone = (CustomLevel) obj;
        assertEquals(Level.INFO.level, clone.level);
        assertEquals(Level.INFO.levelStr, clone.levelStr);
        assertEquals(Level.INFO.syslogEquivalent, clone.syslogEquivalent);
    }
