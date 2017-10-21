    @Test
    public void testDeepLoop()
    {
        String[] s = { "A","B","C","D","E" };
        TopologicalSort<String> ts = new TopologicalSort<>();
        ts.addDependency("B","A");
        ts.addDependency("C","B");
        ts.addDependency("D","C");
        ts.addDependency("E","D");
        ts.addDependency("A","E");
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
