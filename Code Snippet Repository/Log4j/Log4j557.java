    @Test
    public void testGetCurrentStackTrace() throws Exception {
        final Stack<Class<?>> classes = StackLocatorUtil.getCurrentStackTrace();
        final Stack<Class<?>> reversed = new Stack<>();
        reversed.ensureCapacity(classes.size());
        while (!classes.empty()) {
            reversed.push(classes.pop());
        }
        while (reversed.peek() != StackLocatorUtil.class) {
            reversed.pop();
        }
        reversed.pop(); // ReflectionUtil
        assertSame(StackLocatorUtilTest.class, reversed.pop());
    }
