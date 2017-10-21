    public void testEntrySetRemoveAll() {
        DirtyFlagMap<String, String> dirtyFlagMap = new DirtyFlagMap<String, String>();
        Set<Map.Entry<String, String>> entrySet = dirtyFlagMap.entrySet();
        entrySet.removeAll(Collections.EMPTY_LIST);
        assertFalse(dirtyFlagMap.isDirty());
        dirtyFlagMap.put("a", "Y");
        dirtyFlagMap.clearDirtyFlag();
        entrySet.removeAll(Collections.EMPTY_LIST);
        assertFalse(dirtyFlagMap.isDirty());
        entrySet.removeAll(Collections.singletonList(entrySet.iterator().next()));
        assertTrue(dirtyFlagMap.isDirty());
    }
