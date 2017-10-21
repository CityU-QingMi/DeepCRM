    public void testWithProxy() {

        InvocationHandler handler = new InvocationHandler() {
            // dummy implementation
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        };
        // proxy is proxy to an impl of ClassA
        InterfaceA proxy = (InterfaceA) Proxy.newProxyInstance(ClassA.class.getClassLoader(), new Class[] {
                InterfaceA.class, InterfaceB.class, InterfaceC.class }, handler);

        // first, without the recursion
        Method[] smdMethodsA = JSONUtil.listSMDMethods(proxy.getClass(), true);
        assertEquals(0, smdMethodsA.length);

        // now with the recursion
        Method[] smdMethodsB = JSONUtil.listSMDMethods(proxy.getClass(), false);
        assertEquals(1, smdMethodsB.length);
        assertEquals("getB", smdMethodsB[0].getName());
    }
