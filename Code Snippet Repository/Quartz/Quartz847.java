    public void testClear() {
        DirtyFlagMap<String, String> dirtyFlagMap = new DirtyFlagMap<String, String>();
        assertFalse(dirtyFlagMap.isDirty());
        
        dirtyFlagMap.clear();
        assertFalse(dirtyFlagMap.isDirty());
        dirtyFlagMap.put("X", "Y");
        dirtyFlagMap.clearDirtyFlag();
        dirtyFlagMap.clear();
        assertTrue(dirtyFlagMap.isDirty());
    }
