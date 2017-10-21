    @Test
    public void testSimpleLoop()
    {
        String[] s = { "A","B","C","D","E" };
        TopologicalSort<String> ts = new TopologicalSort<>();
        ts.addDependency("B","A");
        ts.addDependency("A","B");
        
        try
        {
            ts.sort(s);
            Assert.fail();
        }
        catch(IllegalStateException e)
        {
            Assert.assertThat(e.getMessage(),Matchers.containsString("cyclic"));
        }
    }
