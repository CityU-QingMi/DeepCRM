    @Test
    public void intArgReturnsSortedArrayStringMapIfPropertySpecifiedButMissingIntConstructor() throws Exception {
        System.setProperty("log4j2.ContextData", FactoryTestStringMapWithoutIntConstructor.class.getName());
        assertTrue(ContextDataFactory.createContextData(2) instanceof SortedArrayStringMap);
        final SortedArrayStringMap actual = (SortedArrayStringMap) ContextDataFactory.createContextData(2);
        final Field thresholdField = SortedArrayStringMap.class.getDeclaredField("threshold");
        thresholdField.setAccessible(true);
        assertEquals(2, thresholdField.getInt(actual));
        System.clearProperty("log4j2.ContextData");
    }
