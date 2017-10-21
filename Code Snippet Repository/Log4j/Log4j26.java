    @Test
    public void testDeserializeINFO() throws Exception {
        final Object obj =
            SerializationTestHelper.deserializeStream(
                "target/test-classes/witness/serialization/info.bin");
        assertTrue(obj instanceof Level);
        final Level info = (Level) obj;
        assertEquals("INFO", info.toString());
        //
        //  JDK 1.1 doesn't support readResolve necessary for the assertion
        if (!System.getProperty("java.version").startsWith("1.1.")) {
            assertTrue(obj == Level.INFO);
        }
    }
