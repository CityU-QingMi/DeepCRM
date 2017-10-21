    public DefaultArtifactResolver()
    {
        int threads = Integer.getInteger( "maven.artifact.threads", 5 );
        if ( threads <= 1 )
        {
            executor = new Executor()
            {
                public void execute( Runnable command )
                {
                    command.run();
                }
            };
        }
        else
        {
            executor = new ThreadPoolExecutor( threads, threads, 3, TimeUnit.SECONDS,
                                               new LinkedBlockingQueue<Runnable>(), new DaemonThreadCreator() );
        }
    }
