        @Override
        public void println( float x )
        {
            final PrintStream currentStream = getOutputStreamForCurrentThread();
            synchronized ( currentStream )
            {
                currentStream.println( x );
                currentStream.notifyAll();
            }
        }
