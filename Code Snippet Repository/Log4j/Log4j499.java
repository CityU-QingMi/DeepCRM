    @Test
    public void testAsList() {
        final MutableThreadContextStack stack = new MutableThreadContextStack(new ArrayList<String>());
        stack.clear();
        assertTrue(stack.isEmpty());
        stack.push("msg1");
        stack.add("msg2");
        stack.push("msg3");

        assertEquals(Arrays.asList("msg1", "msg2", "msg3"), stack.asList());
    }
