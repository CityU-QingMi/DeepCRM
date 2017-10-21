    public void testConstructorWithAStack() {
        OgnlValueStack stack = createValueStack();
        stack.push("Hello World");

        OgnlValueStack stack2 = new OgnlValueStack(stack,
                container.getInstance(XWorkConverter.class),
                (CompoundRootAccessor) container.getInstance(PropertyAccessor.class, CompoundRoot.class.getName()), true);
        container.inject(stack2);

        assertEquals(stack.getRoot(), stack2.getRoot());
        assertEquals(stack.peek(), stack2.peek());
        assertEquals("Hello World", stack2.pop());

    }
