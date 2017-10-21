    @Test
    public void testExceptionWhileAccepting() throws Exception
    {
        Server server = new Server();
        try (StacklessLogging stackless = new StacklessLogging(AbstractConnector.class))
        {
            AtomicLong spins = new AtomicLong();
            ServerConnector connector = new ServerConnector(server)
            {
                @Override
                public void accept(int acceptorID) throws IOException
                {
                    spins.incrementAndGet();
                    throw new IOException("explicitly_thrown_by_test");
                }
            };
            server.addConnector(connector);
            server.start();

            Thread.sleep(1000);
            assertThat(spins.get(), Matchers.lessThan(5L));
        }
        finally
        {
            server.stop();
        }
    }
