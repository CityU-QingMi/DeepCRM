    public void testSerialization() throws Exception {
        // given
        DefaultActionInvocation actionInvocation = new DefaultActionInvocation(new HashMap<String, Object>(), false);
        actionInvocation.setContainer(new MockContainer());

        // when
        DefaultActionInvocation serializable = (DefaultActionInvocation) actionInvocation.serialize();

        // then
        assertNull(actionInvocation.container);
        assertNull(serializable.container);
    }
