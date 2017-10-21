    @Test
    public void testAsList() {
        final DefaultThreadContextStack stack = new DefaultThreadContextStack(true);
        stack.clear();
        assertTrue(stack.isEmpty());
        stack.push("msg1");
        stack.add("msg2");
        stack.push("msg3");

        assertEquals(Arrays.asList("msg1", "msg2", "msg3"), stack.asList());
    }
