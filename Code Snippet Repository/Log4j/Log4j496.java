    @Test
    public void testToStringShowsListContents() {
        final MutableThreadContextStack stack = new MutableThreadContextStack(new ArrayList<String>());
        assertEquals("[]", stack.toString());

        stack.push("msg1");
        stack.add("msg2");
        stack.push("msg3");
        assertEquals("[msg1, msg2, msg3]", stack.toString());

        stack.retainAll(Arrays.asList("msg1", "msg3"));
        assertEquals("[msg1, msg3]", stack.toString());
    }
