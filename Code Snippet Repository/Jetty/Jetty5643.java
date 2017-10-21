    @Test
    public void testDisjointMixed()
    {
        String[] s = { "CC","A","AA","C","BB","B"};
        
        TopologicalSort<String> ts = new TopologicalSort<>();
        ts.addDependency("B","A");
        ts.addDependency("C","B");
        ts.addDependency("BB","AA");
        ts.addDependency("CC","BB");
        
        ts.sort(s);

        // Check direct ordering
        Assert.assertThat(indexOf(s,"A"),lessThan(indexOf(s,"B"))); 
        Assert.assertThat(indexOf(s,"B"),lessThan(indexOf(s,"C"))); 
        Assert.assertThat(indexOf(s,"AA"),lessThan(indexOf(s,"BB"))); 
        Assert.assertThat(indexOf(s,"BB"),lessThan(indexOf(s,"CC"))); 
    }
