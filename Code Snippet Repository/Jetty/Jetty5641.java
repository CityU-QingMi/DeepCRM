    @Test
    public void testDisjoint()
    {
        String[] s = { "A","C","B","CC","AA","BB"};
        
        TopologicalSort<String> ts = new TopologicalSort<>();
        ts.addDependency("B","A");
        ts.addDependency("C","B");
        ts.addDependency("BB","AA");
        ts.addDependency("CC","BB");
        
        ts.sort(s);
        
        Assert.assertThat(s,Matchers.arrayContaining("A","B","C","AA","BB","CC"));   
    }
