    public void testSetParameterAndAttributeNames() throws Exception {
        // given
        prepare("world", Locale.CHINA);

        interceptor.setAttributeName("hello");
        interceptor.setParameterName("world");

        // when
        interceptor.intercept(mai);

        // then
        assertFalse(mai.getInvocationContext().getParameters().contains("world")); // should have been removed

        assertNotNull(session.get("hello")); // should be stored here
        assertEquals(Locale.CHINA, session.get("hello"));
    }
