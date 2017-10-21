        public void run()
        {
            running = true;
            for ( ProjectSegment projectBuild : projectBuildList )
            {
                final PrintStream projectStream = printStreams.get( projectBuild );
                ByteArrayOutputStream projectOs = streams.get( projectBuild );

                do
                {
                    synchronized ( projectStream )
                    {
                        try
                        {
                            projectStream.wait( 100 );
                        }
                        catch ( InterruptedException e )
                        {
                            throw new RuntimeException( e );
                        }
                        try
                        {
                            projectOs.writeTo( originalSystemOUtStream );
                        }
                        catch ( IOException e )
                        {
                            throw new RuntimeException( e );
                        }

                        projectOs.reset();
                    }
                }
                while ( !completedBuilds.contains( projectBuild ) );
            }
            running = false;
        }
