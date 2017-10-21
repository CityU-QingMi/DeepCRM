    @Test
    public void testAsMap() throws Exception {
        final AppenderControlArraySet set = new AppenderControlArraySet();
        final AppenderControl[] controls = new AppenderControl[] {createControl("A"), createControl("B"),
                createControl("C"), createControl("D")};
        for (final AppenderControl ctl : controls) {
            set.add(ctl);
        }
        final Map<String, Appender> expected = new HashMap<>();
        for (final AppenderControl ctl : controls) {
            expected.put(ctl.getAppenderName(), ctl.getAppender());
        }
        assertEquals(expected, set.asMap());
    }
