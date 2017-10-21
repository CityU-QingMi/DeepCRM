    @SuppressWarnings("")
    @Test
    public void mdc() {
        ThreadContext.put("TestYear", Integer.toString(2010));
        logger.debug("Debug message");
        ThreadContext.clearMap();
        logger.debug("Debug message");
        assertThat(list.strList, hasSize(2));
        assertTrue("Incorrect year", list.strList.get(0).startsWith("2010"));
    }
