    public void testKeySetClear() {
        DirtyFlagMap<String, String> dirtyFlagMap = new DirtyFlagMap<String, String>();
        Set<?> keySet = dirtyFlagMap.keySet();
        keySet.clear();
        assertFalse(dirtyFlagMap.isDirty());
        dirtyFlagMap.put("a", "Y");
        dirtyFlagMap.clearDirtyFlag();
        keySet.clear();
        assertTrue(dirtyFlagMap.isDirty());
        assertEquals(0, dirtyFlagMap.size());
    }    
