    public void testEntrySetRetainAll() {
        DirtyFlagMap<String, String> dirtyFlagMap = new DirtyFlagMap<String, String>();
        Set<Map.Entry<String, String>> entrySet = dirtyFlagMap.entrySet();
        entrySet.retainAll(Collections.EMPTY_LIST);
        assertFalse(dirtyFlagMap.isDirty());
        dirtyFlagMap.put("a", "Y");
        dirtyFlagMap.clearDirtyFlag();
        entrySet.retainAll(Collections.singletonList(entrySet.iterator().next()));
        assertFalse(dirtyFlagMap.isDirty());
        entrySet.retainAll(Collections.EMPTY_LIST);
        assertTrue(dirtyFlagMap.isDirty());
    }
