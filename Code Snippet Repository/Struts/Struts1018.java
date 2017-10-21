    public void testActionMessages() {
        assertEquals(false, as.hasActionMessages());
        assertEquals(0, as.getActionMessages().size());
        as.addActionMessage("Killroy was here");
        assertEquals(1, as.getActionMessages().size());
        assertEquals("Killroy was here", as.getActionMessages().iterator().next());
        assertEquals(true, as.hasActionMessages());

        assertEquals(false, as.hasActionErrors()); // does not count as a error
        assertEquals(false, as.hasErrors()); // does not count as a error

        as.clearErrorsAndMessages();
        assertEquals(false, as.hasActionMessages());
        assertEquals(false, as.hasErrors());
    }
