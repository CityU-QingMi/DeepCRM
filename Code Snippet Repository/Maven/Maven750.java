        public void waitUntilRunning( boolean expect )
        {
            while ( !running == expect )
            {
                try
                {
                    Thread.sleep( 10 );
                }
                catch ( InterruptedException e )
                {
                    throw new RuntimeException( e );
                }
            }
        }
