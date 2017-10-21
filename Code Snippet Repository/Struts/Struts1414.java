    public void testOgnlUtilEmptyStringAsLong() {
        Bar bar = new Bar();
        Map context = Ognl.createDefaultContext(bar);
        context.put(XWorkConverter.REPORT_CONVERSION_ERRORS, Boolean.TRUE);
        bar.setId(null);

        HashMap props = new HashMap();
        props.put("id", "");

        ognlUtil.setProperties(props, bar, context);
        assertNull(bar.getId());
        assertEquals(0, bar.getFieldErrors().size());

        props.put("id", new String[]{""});

        bar.setId(null);
        ognlUtil.setProperties(props, bar, context);
        assertNull(bar.getId());
        assertEquals(0, bar.getFieldErrors().size());
    }
