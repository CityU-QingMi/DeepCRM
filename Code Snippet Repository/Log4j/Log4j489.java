    static DefaultThreadContextStack createStack() {
        final DefaultThreadContextStack stack = new DefaultThreadContextStack(true);
        stack.clear();
        assertTrue(stack.isEmpty());
        stack.push("msg1");
        stack.add("msg2");
        stack.push("msg3");
        assertEquals(3, stack.size());
        return stack;
    }
