    @Test
    public void testGetReturnsAddedItems() throws Exception {
        final AppenderControlArraySet set = new AppenderControlArraySet();
        final AppenderControl[] controls = new AppenderControl[] {createControl("A"), createControl("B"),
                createControl("C")};
        for (final AppenderControl ctl : controls) {
            set.add(ctl);
        }
        assertEquals(3, set.get().length);
        assertArrayEquals(controls, set.get());
    }
