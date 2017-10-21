    public void testComplexMap() throws Exception {
        Set<MyObject> s = new LinkedHashSet<MyObject>();
        s.add(new MyObject("tm_jee", 20));
        s.add(new MyObject("jenny", 22));

        assertFalse(ContainUtil.contains(s, new MyObject("paul", 50)));
        assertFalse(ContainUtil.contains(s, new MyObject("tm_jee", 44)));
        assertTrue(ContainUtil.contains(s, new MyObject("tm_jee", 20)));
        assertTrue(ContainUtil.contains(s, new MyObject("jenny", 22)));
    }
