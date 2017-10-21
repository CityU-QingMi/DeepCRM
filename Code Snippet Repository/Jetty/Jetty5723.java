    @Test
    public void testCondensePackage()
    {
        String cases[][] = new String[][]{
                {null, ""},
                {"org.eclipse.Foo.\u0000", "oe.Foo"},
                {".foo", "foo"},
                {".bar.Foo", "b.Foo"},
                {"org...bar..Foo", "ob.Foo"}
        };
    
        StdErrLog log = new StdErrLog();
        
        for (int i = 0; i < cases.length; i++)
        {
            // System.err.printf("newLogger(%s)%n", cases[i][0]);
            StdErrLog logger = (StdErrLog) log.newLogger(cases[i][0]);
            assertThat("log[" + cases[i][0] + "] condenses to name", logger._abbrevname, is(cases[i][1]));
        }
    }
