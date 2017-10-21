    public void testComplexList() throws Exception {
        List<MyObject> l = new ArrayList<MyObject>();
        l.add(new MyObject("tm_jee", 20));
        l.add(new MyObject("jenny", 22));

        assertFalse(ContainUtil.contains(l, new MyObject("paul", 50)));
        assertFalse(ContainUtil.contains(l, new MyObject("tm_jee", 44)));
        assertTrue(ContainUtil.contains(l, new MyObject("tm_jee", 20)));
        assertTrue(ContainUtil.contains(l, new MyObject("jenny", 22)));
    }
