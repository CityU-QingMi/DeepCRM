    @SuppressWarnings("")
    @Override
    protected void verifyMatch(Object target, Object deserialized) {
        JobDataMap targetMap = (JobDataMap)target;
        JobDataMap deserializedMap = (JobDataMap)deserialized;
        
        assertNotNull(deserializedMap);
        assertEquals(targetMap.getWrappedMap(), deserializedMap.getWrappedMap());
        assertEquals(targetMap.getAllowsTransientData(), deserializedMap.getAllowsTransientData());
        assertEquals(targetMap.isDirty(), deserializedMap.isDirty());
    }
