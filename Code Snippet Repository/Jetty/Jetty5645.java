    @Test
    public void testPreserveOrder()
    {
        String[] s = { "Deep","Foobar","Wibble","Bozo","XXX","12345","Test"};
        
        TopologicalSort<String> ts = new TopologicalSort<>();
        ts.addDependency("Deep","Test");
        ts.addDependency("Deep","Wibble");
        ts.addDependency("Deep","12345");
        ts.addDependency("Deep","XXX");
        ts.addDependency("Deep","Foobar");
        ts.addDependency("Deep","Bozo");
        
        ts.sort(s);
        Assert.assertThat(s,Matchers.arrayContaining("Foobar","Wibble","Bozo","XXX","12345","Test","Deep")); 
    }
