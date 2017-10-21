    @Test
    public void testTree()
    {
        String[] s = { "LeafA0","LeafB0","LeafA1","Root","BranchA","LeafB1","BranchB"};
        
        TopologicalSort<String> ts = new TopologicalSort<>();
        ts.addDependency("BranchB","Root");
        ts.addDependency("BranchA","Root");
        ts.addDependency("LeafA1","BranchA");
        ts.addDependency("LeafA0","BranchA");
        ts.addDependency("LeafB0","BranchB");
        ts.addDependency("LeafB1","BranchB");
        
        ts.sort(s);
        
        // Check direct ordering
        Assert.assertThat(indexOf(s,"Root"),lessThan(indexOf(s,"BranchA")));  
        Assert.assertThat(indexOf(s,"Root"),lessThan(indexOf(s,"BranchB")));   
        Assert.assertThat(indexOf(s,"BranchA"),lessThan(indexOf(s,"LeafA0")));    
        Assert.assertThat(indexOf(s,"BranchA"),lessThan(indexOf(s,"LeafA1"))); 
        Assert.assertThat(indexOf(s,"BranchB"),lessThan(indexOf(s,"LeafB0")));    
        Assert.assertThat(indexOf(s,"BranchB"),lessThan(indexOf(s,"LeafB1")));   
        
        // check remnant ordering of original list
        Assert.assertThat(indexOf(s,"BranchA"),lessThan(indexOf(s,"BranchB")));  
        Assert.assertThat(indexOf(s,"LeafA0"),lessThan(indexOf(s,"LeafA1")));  
        Assert.assertThat(indexOf(s,"LeafB0"),lessThan(indexOf(s,"LeafB1")));  
    }
