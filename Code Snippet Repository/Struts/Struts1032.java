    public void testDeserialization() throws Exception {
        // given
        DefaultActionInvocation actionInvocation = new DefaultActionInvocation(new HashMap<String, Object>(), false);
        MockContainer mockContainer = new MockContainer();
        ActionContext.getContext().setContainer(mockContainer);

        // when
        DefaultActionInvocation deserializable = (DefaultActionInvocation) actionInvocation.deserialize(ActionContext.getContext());

        // then
        assertNotNull(actionInvocation.container);
        assertNotNull(deserializable.container);
        assertEquals(mockContainer, deserializable.container);
    }
