    @Test
    public void testPushAndAddIncreaseStack() {
        final MutableThreadContextStack stack = new MutableThreadContextStack(new ArrayList<String>());
        stack.clear();
        assertTrue(stack.isEmpty());
        stack.push("msg1");
        stack.add("msg2");

        assertEquals(2, stack.size());
    }
