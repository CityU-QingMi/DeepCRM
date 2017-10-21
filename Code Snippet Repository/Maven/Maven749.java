        @Override
        public void print( char s[] )
        {
            final PrintStream currentStream = getOutputStreamForCurrentThread();
            synchronized ( currentStream )
            {
                currentStream.print( s );
                currentStream.notifyAll();
            }
        }
