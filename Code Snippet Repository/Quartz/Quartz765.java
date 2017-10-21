    @Override
    protected void verifyMatch(Object target, Object deserialized) {
        DailyCalendar targetCalendar = (DailyCalendar)target;
        DailyCalendar deserializedCalendar = (DailyCalendar)deserialized;
        
        assertNotNull(deserializedCalendar);
        assertEquals(targetCalendar.getDescription(), deserializedCalendar.getDescription());
        assertTrue(deserializedCalendar.getInvertTimeRange());
        assertNull(deserializedCalendar.getTimeZone());
        assertTrue(deserializedCalendar.toString().indexOf("01:20:01:456 - 14:50:15:002") > 0);
    }
