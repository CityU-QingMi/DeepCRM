    @Test
    public void testClearRemovesAllItems() throws Exception {
        final AppenderControlArraySet set = new AppenderControlArraySet();
        set.add(createControl("A"));
        set.add(createControl("B"));
        set.add(createControl("C"));
        assertFalse(set.isEmpty());

        set.clear();
        assertTrue(set.isEmpty());
    }
