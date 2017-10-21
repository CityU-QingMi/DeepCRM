        @Override
        public void println( double x )
        {
            final PrintStream currentStream = getOutputStreamForCurrentThread();
            synchronized ( currentStream )
            {
                currentStream.println( x );
                currentStream.notifyAll();
            }
        }
