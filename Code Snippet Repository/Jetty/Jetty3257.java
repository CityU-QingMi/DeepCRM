    @Test
    public void testNested() throws Exception
    {
        Server server = new Server();
        ClassLoader middleLoader = new ClassLoader(Server.class.getClassLoader())
        {
            public String toString()
            {
                return "MiddleLoader";
            }
        };
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
        assertThat(dump,containsString("|   +- MiddleLoader"));
        assertThat(dump,containsString("|       +- "+Server.class.getClassLoader()));
        assertThat(dump,containsString("+> "+Server.class.getClassLoader()));
    }
