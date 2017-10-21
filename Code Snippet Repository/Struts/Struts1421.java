    public void testContains() {
        ValueStack vs = ActionContext.getContext().getValueStack();
        ListHolder listHolder = new ListHolder();
        vs.push(listHolder);

        vs.setValue("longs", new String[] {"1", "2", "3"});

        assertNotNull(listHolder.getLongs());
        assertEquals(3, listHolder.getLongs().size());
        assertEquals(new Long(1), (Long) listHolder.getLongs().get(0));
        assertEquals(new Long(2), (Long) listHolder.getLongs().get(1));
        assertEquals(new Long(3), (Long) listHolder.getLongs().get(2));

        assertTrue(((Boolean) vs.findValue("longs.contains(1)")).booleanValue());
    }
