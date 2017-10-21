    @Test
    public void testAddDoesNotAppendersWithSameName() throws Exception {
        final AppenderControlArraySet set = new AppenderControlArraySet();
        final AppenderControl[] controls = new AppenderControl[] {createControl("A"), createControl("B"),
                createControl("B"), createControl("B"), createControl("A")};
        for (final AppenderControl ctl : controls) {
            set.add(ctl);
        }
        assertEquals(2, set.get().length);
        assertSame(controls[0], set.get()[0]);
        assertSame(controls[1], set.get()[1]);
    }
