    @Override
    protected void verifyMatch(Object target, Object deserialized) {
        AnnualCalendar targetCalendar = (AnnualCalendar)target;
        AnnualCalendar deserializedCalendar = (AnnualCalendar)deserialized;
        
        assertNotNull(deserializedCalendar);
        assertEquals(targetCalendar.getDescription(), deserializedCalendar.getDescription());
        assertEquals(targetCalendar.getDaysExcluded(), deserializedCalendar.getDaysExcluded());
        assertNull(deserializedCalendar.getTimeZone());
    }
