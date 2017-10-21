    @Test
    public void testNestedLoggingInLastArgument() {
        final ParameterizedLoggingThing it = new ParameterizedLoggingThing();
        logger.info("main: argCount={} it={}", "2", it);
        final List<String> list = listAppender.getMessages();

        final String expect1 = "DEBUG org.apache.logging.log4j.core.impl.NestedLoggingFromToStringTest.ParameterizedLoggingThing getX: values x=3 y=4 z=5";
        final String expect2 = "INFO org.apache.logging.log4j.core.impl.NestedLoggingFromToStringTest main: argCount=2 it=[ParameterizedLoggingThing x=3 y=4 z=5]";
        assertEquals(expect1, list.get(0));
        assertEquals(expect2, list.get(1));
    }
