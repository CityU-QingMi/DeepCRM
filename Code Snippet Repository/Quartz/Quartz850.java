    public void testRemove() {
        DirtyFlagMap<String, String> dirtyFlagMap = new DirtyFlagMap<String, String>();
        dirtyFlagMap.put("a", "Y");
        dirtyFlagMap.clearDirtyFlag();
        
        dirtyFlagMap.remove("b");
        assertFalse(dirtyFlagMap.isDirty());

        dirtyFlagMap.remove("a");
        assertTrue(dirtyFlagMap.isDirty());
    }
