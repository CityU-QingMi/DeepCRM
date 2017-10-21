    @SuppressWarnings("")
    public void testEntrySetToArray() {
        DirtyFlagMap<String, String> dirtyFlagMap = new DirtyFlagMap<String, String>();
        Set<Map.Entry<String, String>> entrySet = dirtyFlagMap.entrySet();
        dirtyFlagMap.put("a", "A");
        dirtyFlagMap.put("b", "B");
        dirtyFlagMap.put("c", "C");
        dirtyFlagMap.clearDirtyFlag();
        Object[] array = entrySet.toArray();
        assertEquals(3, array.length);
        Map.Entry<?, String> entry = (Map.Entry<?, String>)array[0];
        entry.setValue("BB");
        assertTrue(dirtyFlagMap.isDirty());
        assertTrue(dirtyFlagMap.containsValue("BB"));
    }
