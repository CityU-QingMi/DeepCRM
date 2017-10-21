    @Test
    public void testAddReturnsFalseIfAlreadyInSet() throws Exception {
        final AppenderControlArraySet set = new AppenderControlArraySet();
        assertTrue(set.add(createControl("A")));
        assertTrue(set.add(createControl("B")));
        assertFalse(set.add(createControl("B")));
        assertFalse(set.add(createControl("B")));
        assertFalse(set.add(createControl("A")));
        assertEquals(2, set.get().length);
    }
