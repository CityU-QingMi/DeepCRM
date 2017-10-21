    @Test
    public void testDoesNothingIfConstructedWithUseStackIsFalse() {
        final DefaultThreadContextStack stack = new DefaultThreadContextStack(false);
        stack.clear();
        assertTrue(stack.isEmpty());
        stack.push("msg");

        // nothing was added
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }
