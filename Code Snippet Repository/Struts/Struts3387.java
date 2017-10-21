    public void testBaseClassOnly() {
        Method[] smdMethodsA = JSONUtil.listSMDMethods(ClassA.class, true);
        assertEquals(2, smdMethodsA.length);
        assertEquals("getZ", smdMethodsA[0].getName());
        assertEquals("getX", smdMethodsA[1].getName());

        Method[] smdMethodsB = JSONUtil.listSMDMethods(ClassB.class, true);
        assertEquals(1, smdMethodsB.length);
        assertEquals("getX", smdMethodsB[0].getName());

        Method[] smdMethodsC = JSONUtil.listSMDMethods(ClassC.class, true);
        assertEquals(1, smdMethodsC.length);
        assertEquals("getX", smdMethodsC[0].getName());
    }
