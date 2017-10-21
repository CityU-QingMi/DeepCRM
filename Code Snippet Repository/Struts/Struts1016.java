    public void testNothingDoneOnActionSupport() throws Exception {
        assertEquals(false, as.hasErrors());

        assertNotNull(as.getActionErrors());
        assertEquals(0, as.getActionErrors().size());
        assertEquals(false, as.hasActionErrors());

        assertNotNull(as.getActionMessages());
        assertEquals(0, as.getActionMessages().size());
        assertEquals(false, as.hasActionMessages());

        assertNotNull(as.getFieldErrors());
        assertEquals(0, as.getFieldErrors().size());
        assertEquals(false, as.hasFieldErrors());

        assertNull(as.getText(null));

        try {
            as.pause(null);
        } catch (Exception e) {
            fail("Should not fail");
        }

        assertEquals(Action.INPUT, as.input());
        assertEquals(Action.SUCCESS, as.execute());
        try {
            as.clone();
            fail("Failure expected for clone()");
        } catch (CloneNotSupportedException e) {
            // success!
        }


        assertNull(as.getText(null, (List) null));
        assertNull(as.getText(null, (String) null));
        assertNull(as.getText(null, (String[]) null));

        assertNull(as.getText(null, (String) null, (List) null));
        assertNull(as.getText(null, (String) null, (String) null));
        assertNull(as.getText(null, (String) null, (String[]) null));

        assertNull(as.getText(null, (String) null, (List) null, (ValueStack) null));
        assertNull(as.getText(null, (String) null, (String[]) null, (ValueStack) null));

        assertNotNull(as.getLocale());
        assertEquals(ActionContext.getContext().getLocale(), as.getLocale());

        assertNull(as.getTexts()); // can not find a bundle
        assertEquals("not.in.bundle", as.getText("not.in.bundle"));
    }
