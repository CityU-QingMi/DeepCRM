        @Override
        public void print( char c )
        {
            final PrintStream currentStream = getOutputStreamForCurrentThread();
            synchronized ( currentStream )
            {
                currentStream.print( c );
                currentStream.notifyAll();
            }
        }
