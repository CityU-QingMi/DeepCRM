    @Test(expected = UnsupportedOperationException.class)
    public void testModifyingImmutableOrNullThrowsException() {
        final DefaultThreadContextStack stack = createStack();
        final int originalSize = stack.size();
        assertTrue(originalSize > 0);
        final ContextStack actual = stack.getImmutableStackOrNull();
        assertEquals(originalSize, actual.size());

        actual.pop();
    }
