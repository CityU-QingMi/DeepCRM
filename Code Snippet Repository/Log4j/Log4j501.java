    static MutableThreadContextStack createStack() {
        final MutableThreadContextStack stack1 = new MutableThreadContextStack(new ArrayList<String>());
        stack1.clear();
        assertTrue(stack1.isEmpty());
        stack1.push("msg1");
        stack1.add("msg2");
        stack1.push("msg3");
        assertEquals(3, stack1.size());
        return stack1;
    }
