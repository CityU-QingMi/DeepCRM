    @Test
    public void testRemoveReturnsRemovedItem() throws Exception {
        final AppenderControlArraySet set = new AppenderControlArraySet();
        final AppenderControl[] controls = new AppenderControl[] {createControl("A"), createControl("B"),
                createControl("C"), createControl("D")};
        for (final AppenderControl ctl : controls) {
            set.add(ctl);
        }
        assertEquals(controls.length, set.get().length);

        final AppenderControl b = set.remove("B");
        assertSame(controls[1], b);

        final AppenderControl c = set.remove("C");
        assertSame(controls[2], c);
    }
