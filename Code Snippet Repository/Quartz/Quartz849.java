    public void testValuesClear() {
        DirtyFlagMap<String, String> dirtyFlagMap = new DirtyFlagMap<String, String>();
        Collection<?> values = dirtyFlagMap.values();
        values.clear();
        assertFalse(dirtyFlagMap.isDirty());
        dirtyFlagMap.put("a", "Y");
        dirtyFlagMap.clearDirtyFlag();
        values.clear();
        assertTrue(dirtyFlagMap.isDirty());
        assertEquals(0, dirtyFlagMap.size());
    }    
