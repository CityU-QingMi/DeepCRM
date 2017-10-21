    public void testActionErrors() {
        assertEquals(false, as.hasActionErrors());
        assertEquals(0, as.getActionErrors().size());
        as.addActionError("Damm");
        assertEquals(1, as.getActionErrors().size());
        assertEquals("Damm", as.getActionErrors().iterator().next());
        assertEquals(true, as.hasActionErrors());
        assertEquals(true, as.hasErrors());

        as.clearErrorsAndMessages();
        assertEquals(false, as.hasActionErrors());
        assertEquals(false, as.hasErrors());
    }
