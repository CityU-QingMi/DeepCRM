    @Test
    public void testNestedLoggingInFirstArgument() {
        final ParameterizedLoggingThing it = new ParameterizedLoggingThing();
        logger.info("next: it={} some{} other{}", it, "AA", "BB");
        final List<String> list = listAppender.getMessages();

        final String expect1 = "DEBUG org.apache.logging.log4j.core.impl.NestedLoggingFromToStringTest.ParameterizedLoggingThing getX: values x=3 y=4 z=5";
        final String expect2 = "INFO org.apache.logging.log4j.core.impl.NestedLoggingFromToStringTest next: it=[ParameterizedLoggingThing x=3 y=4 z=5] someAA otherBB";
        assertEquals(expect1, list.get(0));
        assertEquals(expect2, list.get(1));
    }
