    @Test
    public void testGetCurrentStackTrace() throws Exception {
        final Stack<Class<?>> classes = stackLocator.getCurrentStackTrace();
        final Stack<Class<?>> reversed = new Stack<>();
        reversed.ensureCapacity(classes.size());
        while (!classes.empty()) {
            reversed.push(classes.pop());
        }
        while (reversed.peek() != StackLocator.class) {
            reversed.pop();
        }
        reversed.pop(); // ReflectionUtil
        assertSame(StackLocatorTest.class, reversed.pop());
    }
