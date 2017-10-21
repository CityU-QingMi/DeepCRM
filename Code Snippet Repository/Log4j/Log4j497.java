    @Test
    public void testPeekReturnsLastAddedItem() {
        final MutableThreadContextStack stack = new MutableThreadContextStack(new ArrayList<String>());
        stack.clear();
        assertTrue(stack.isEmpty());
        stack.push("msg1");
        stack.add("msg2");

        assertEquals(2, stack.size());
        assertEquals("msg2", stack.peek());

        stack.push("msg3");
        assertEquals("msg3", stack.peek());
    }
