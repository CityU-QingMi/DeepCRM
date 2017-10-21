    @Test
    public void testSimple() throws Exception
    {
        Server server = new Server();
        ClassLoader loader = new ClassLoader()
        {
            public String toString()
            {
                return "SimpleLoader";
            }
        };

        server.addBean(new ClassLoaderDump(loader));

        StringBuilder out = new StringBuilder();
        server.dump(out);
        String dump = out.toString();
        assertThat(dump,containsString("+- SimpleLoader"));
        assertThat(dump,containsString("+> "+Server.class.getClassLoader()));
    }
