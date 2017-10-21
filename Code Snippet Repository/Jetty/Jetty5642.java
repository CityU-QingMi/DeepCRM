    @Test
    public void testDisjointReversed()
    {
        String[] s = { "CC","AA","BB","A","C","B"};
        
        TopologicalSort<String> ts = new TopologicalSort<>();
        ts.addDependency("B","A");
        ts.addDependency("C","B");
        ts.addDependency("BB","AA");
        ts.addDependency("CC","BB");
        
        ts.sort(s);
          
        Assert.assertThat(s,Matchers.arrayContaining("AA","BB","CC","A","B","C"));  
    }
