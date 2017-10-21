    @Test
    public void testPush() {
        ThreadContext.push("Hello");
        ThreadContext.push("{} is {}", ThreadContextInheritanceTest.class.getSimpleName(),
                "running");
        assertEquals("Incorrect parameterized stack value",
                ThreadContext.pop(), "ThreadContextInheritanceTest is running");
        assertEquals("Incorrect simple stack value", ThreadContext.pop(),
                "Hello");
    }
