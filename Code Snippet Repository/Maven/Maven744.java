        @Override
        public void println( int x )
        {
            final PrintStream currentStream = getOutputStreamForCurrentThread();
            synchronized ( currentStream )
            {
                currentStream.println( x );
                currentStream.notifyAll();
            }
        }
