    @Test
    public void testUrlClassLoaders() throws Exception
    {
        Server server = new Server();
        ClassLoader middleLoader = new URLClassLoader(new URL[] 
            {new URL("file:/one"),new URL("file:/two"),new URL("file:/three"),},
            Server.class.getClassLoader())
        {
            public String toString()
            {
                return "MiddleLoader";
            }
        };
        ClassLoader loader = new URLClassLoader(new URL[] 
            {new URL("file:/ONE"),new URL("file:/TWO"),new URL("file:/THREE"),},
            middleLoader)
        {
            public String toString()
            {
                return "TopLoader";
            }
        };

        server.addBean(new ClassLoaderDump(loader));

        StringBuilder out = new StringBuilder();
        server.dump(out);
        String dump = out.toString();
        // System.err.println(dump);
        assertThat(dump,containsString("+- TopLoader"));
        assertThat(dump,containsString("|   +- file:/ONE"));
        assertThat(dump,containsString("|   +- file:/TWO"));
        assertThat(dump,containsString("|   +- file:/THREE"));
        assertThat(dump,containsString("|   +- MiddleLoader"));
        assertThat(dump,containsString("|       +- file:/one"));
        assertThat(dump,containsString("|       +- file:/two"));
        assertThat(dump,containsString("|       +- file:/three"));
        assertThat(dump,containsString("|       +- "+Server.class.getClassLoader()));
        assertThat(dump,containsString("+> "+Server.class.getClassLoader()));
    }
