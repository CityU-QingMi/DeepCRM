    @Test
    public void testRemove() {
        final MutableThreadContextStack stack = createStack();
        assertTrue(stack.containsAll(Arrays.asList("msg1", "msg2", "msg3")));

        stack.remove("msg1");
        assertEquals(2, stack.size());
        assertTrue(stack.containsAll(Arrays.asList("msg2", "msg3")));
        assertEquals("msg3", stack.peek());

        stack.remove("msg3");
        assertEquals(1, stack.size());
        assertTrue(stack.containsAll(Arrays.asList("msg2")));
        assertEquals("msg2", stack.peek());
    }
