    public void testEntrySetClear() {
        DirtyFlagMap<String, String> dirtyFlagMap = new DirtyFlagMap<String, String>();
        Set<Map.Entry<String, String>> entrySet = dirtyFlagMap.entrySet();
        entrySet.clear();
        assertFalse(dirtyFlagMap.isDirty());
        dirtyFlagMap.put("a", "Y");
        dirtyFlagMap.clearDirtyFlag();
        entrySet.clear();
        assertTrue(dirtyFlagMap.isDirty());
    }        
