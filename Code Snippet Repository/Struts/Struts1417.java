    public void testValueStackSetValueEmptyStringAsLong() {
        Bar bar = new Bar();
        ValueStack vs = ActionContext.getContext().getValueStack();
        vs.getContext().put(XWorkConverter.REPORT_CONVERSION_ERRORS, Boolean.TRUE);
        vs.push(bar);

        vs.setValue("id", "");
        assertNull(bar.getId());
        assertEquals(0, bar.getFieldErrors().size());

        bar.setId(null);

        vs.setValue("id", new String[]{""});
        assertNull(bar.getId());
        assertEquals(0, bar.getFieldErrors().size());
    }
