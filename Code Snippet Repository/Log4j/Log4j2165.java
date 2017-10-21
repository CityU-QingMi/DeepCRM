    @Test
    public void testDoublyNestedLogging() {
        logger.info(new ObjectLoggingThing1());
        final List<String> list = listAppender.getMessages();

        final String expect1 = "DEBUG org.apache.logging.log4j.core.impl.NestedLoggingFromToStringTest.ParameterizedLoggingThing getX: values x=3 y=4 z=5";
        final String expect2 = "TRACE org.apache.logging.log4j.core.impl.NestedLoggingFromToStringTest.ObjectLoggingThing2 [ParameterizedLoggingThing x=3 y=4 z=5]";
        final String expect3 = "TRACE org.apache.logging.log4j.core.impl.NestedLoggingFromToStringTest.ObjectLoggingThing1 [ObjectLoggingThing2 x=123]";
        final String expect4 = "INFO org.apache.logging.log4j.core.impl.NestedLoggingFromToStringTest [ObjectLoggingThing1 y=999]";
        assertEquals(expect1, list.get(0));
        assertEquals(expect2, list.get(1));
        assertEquals(expect3, list.get(2));
        assertEquals(expect4, list.get(3));
    }
