        @Override
        public void println( char x[] )
        {
            final PrintStream currentStream = getOutputStreamForCurrentThread();
            synchronized ( currentStream )
            {
                currentStream.print( x );
                currentStream.notifyAll();
            }
        }
