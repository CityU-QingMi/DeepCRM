    @SuppressWarnings("")
    public void testEntrySetIterator() {
        DirtyFlagMap<String, String> dirtyFlagMap = new DirtyFlagMap<String, String>();
        Set<Map.Entry<String, String>> entrySet = dirtyFlagMap.entrySet();
        dirtyFlagMap.put("a", "A");
        dirtyFlagMap.put("b", "B");
        dirtyFlagMap.put("c", "C");
        dirtyFlagMap.clearDirtyFlag();
        Iterator<?> entrySetIter = entrySet.iterator();
        Map.Entry<?, ?> entryToBeRemoved = (Map.Entry<?, ?>)entrySetIter.next();
        String removedKey = (String)entryToBeRemoved.getKey();
        entrySetIter.remove();
        assertEquals(2, dirtyFlagMap.size());
        assertTrue(dirtyFlagMap.isDirty());
        assertFalse(dirtyFlagMap.containsKey(removedKey));
        dirtyFlagMap.clearDirtyFlag();
        Map.Entry<?, String> entry = (Map.Entry<?, String>)entrySetIter.next();
        entry.setValue("BB");
        assertTrue(dirtyFlagMap.isDirty());
        assertTrue(dirtyFlagMap.containsValue("BB"));
    }
