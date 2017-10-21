    @Test
    public void testSimpleLinear()
    {
        String[] s = { "D","E","C","B","A" };
        TopologicalSort<String> ts = new TopologicalSort<>();
        ts.addDependency("B","A");
        ts.addDependency("C","B");
        ts.addDependency("D","C");
        ts.addDependency("E","D");
        
        ts.sort(s);
        
        Assert.assertThat(s,Matchers.arrayContaining("A","B","C","D","E"));        
    }
