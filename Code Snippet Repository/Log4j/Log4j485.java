    @Test
    public void testPeekReturnsLastAddedItem() {
        final DefaultThreadContextStack stack = new DefaultThreadContextStack(true);
        stack.clear();
        assertTrue(stack.isEmpty());
        stack.push("msg1");
        stack.add("msg2");

        assertEquals(2, stack.size());
        assertEquals("msg2", stack.peek());

        stack.push("msg3");
        assertEquals("msg3", stack.peek());
    }
