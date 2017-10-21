    @Test
    public void testParent() throws Exception
    {
        Server server = new Server();
        ClassLoader loader = new ClassLoader(Server.class.getClassLoader())
        {
            public String toString()
            {
                return "ParentedLoader";
            }
        };

        server.addBean(new ClassLoaderDump(loader));

        StringBuilder out = new StringBuilder();
        server.dump(out);
        String dump = out.toString();
        assertThat(dump,containsString("+- ParentedLoader"));
        assertThat(dump,containsString("|   +- "+Server.class.getClassLoader()));
        assertThat(dump,containsString("+> "+Server.class.getClassLoader()));
    }
