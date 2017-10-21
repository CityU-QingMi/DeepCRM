    @Test
    public void testRemoveRemovesItemFromSet() throws Exception {
        final AppenderControlArraySet set = new AppenderControlArraySet();
        set.add(createControl("A"));
        set.add(createControl("B"));
        set.add(createControl("C"));
        set.add(createControl("D"));
        assertEquals(4, set.get().length);

        set.remove("B");
        assertEquals(3, set.get().length);
        final AppenderControl[] three = set.get();
        assertEquals("A", three[0].getAppenderName());
        assertEquals("C", three[1].getAppenderName());
        assertEquals("D", three[2].getAppenderName());

        set.remove("C");
        assertEquals(2, set.get().length);
        final AppenderControl[] two = set.get();
        assertEquals("A", two[0].getAppenderName());
        assertEquals("D", two[1].getAppenderName());

        set.remove("A");
        assertEquals(1, set.get().length);
        final AppenderControl[] one = set.get();
        assertEquals("D", one[0].getAppenderName());

        set.remove("D");
        assertTrue(set.isEmpty());
    }
