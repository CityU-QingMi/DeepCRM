    @Test
    public void testDumpable() throws Exception
    {
        Server server = new Server();
        ClassLoader middleLoader = new DumpableClassLoader(Server.class.getClassLoader());
        ClassLoader loader = new ClassLoader(middleLoader)
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
        assertThat(dump,containsString("+- TopLoader"));
        assertThat(dump,containsString("|   +- DumpableClassLoader"));
        assertThat(dump,not(containsString("|       +- "+Server.class.getClassLoader())));
        assertThat(dump,containsString("+> "+Server.class.getClassLoader()));
    }
