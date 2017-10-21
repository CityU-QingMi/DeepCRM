    public void testEntrySetRemove() {
        DirtyFlagMap<String, String> dirtyFlagMap = new DirtyFlagMap<String, String>();
        Set<Map.Entry<String, String>> entrySet = dirtyFlagMap.entrySet();
        dirtyFlagMap.remove("a");
        assertFalse(dirtyFlagMap.isDirty());
        dirtyFlagMap.put("a", "Y");
        dirtyFlagMap.clearDirtyFlag();
        entrySet.remove("b");
        assertFalse(dirtyFlagMap.isDirty());
        entrySet.remove(entrySet.iterator().next());
        assertTrue(dirtyFlagMap.isDirty());
    }
