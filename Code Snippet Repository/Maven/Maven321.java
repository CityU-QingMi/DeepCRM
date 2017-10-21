    public void testMNG4738()
        throws Exception
    {
        Artifact g = createLocalArtifact( "g", "1.0" );
        createLocalArtifact( "h", "1.0" );
        artifactResolver.resolveTransitively( Collections.singleton( g ), projectArtifact, remoteRepositories(),
                                              localRepository(), null );

        // we want to see all top-level thread groups
        ThreadGroup tg = Thread.currentThread().getThreadGroup();
        while ( tg.getParent() == null )
        {
            tg = tg.getParent();
        }

        ThreadGroup[] tgList = new ThreadGroup[tg.activeGroupCount()];
        tg.enumerate( tgList );

        boolean seen = false;

        for ( ThreadGroup aTgList : tgList )
        {
            if ( !aTgList.getName().equals( DaemonThreadCreator.THREADGROUP_NAME ) )
            {
                continue;
            }

            seen = true;

            tg = aTgList;
            Thread[] ts = new Thread[tg.activeCount()];
            tg.enumerate( ts );

            for ( Thread active : ts )
            {
                String name = active.getName();
                boolean daemon = active.isDaemon();
                assertTrue( name + " is no daemon Thread.", daemon );
            }

        }

        assertTrue( "Could not find ThreadGroup: " + DaemonThreadCreator.THREADGROUP_NAME, seen );
    }
