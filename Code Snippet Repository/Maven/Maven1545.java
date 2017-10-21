    public void testConcurrentInterpolation()
        throws Exception
    {
        final Model model = new Model();

        final Properties p = new Properties();
        p.setProperty( "key", "value" );
        p.setProperty( "key2", "value2" );
        p.setProperty( "key3", "value3" );
        p.setProperty( "key4", "value4" );
        p.setProperty( "key5", "value5" );

        final StringSearchModelInterpolator interpolator = (StringSearchModelInterpolator) createInterpolator();


        int numItems = 100;
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        List<Future<SimpleProblemCollector>>  futures = new ArrayList<>();
        for ( int i = 0; i < numItems; i++ )
        {
            Callable<SimpleProblemCollector> future = new Callable<SimpleProblemCollector>()
            {
                public SimpleProblemCollector call()
                    throws Exception
                {
                    final ObjectWithMixedProtection obj = getValueList();
                    final ModelBuildingRequest config = createModelBuildingRequest( p );

                    countDownLatch.await();
                    final SimpleProblemCollector collector = new SimpleProblemCollector();
                    interpolator.interpolateObject( obj, model, new File( "." ), config, collector );
                    return collector;
                }
            };
            FutureTask<SimpleProblemCollector> task = new FutureTask<>( future );
            futures.add( task );
            new Thread( task ).start();
        }
        countDownLatch.countDown(); // Start all the threads
        for ( Future<SimpleProblemCollector> result : futures )
        {
            SimpleProblemCollector problemCollector = result.get(); // ArrayIndexOutOfBoundsException are typical indication of threading issues
            assertProblemFree( problemCollector );
        }
    }
