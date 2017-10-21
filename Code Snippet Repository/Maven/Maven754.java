        @Override
        public void print( String s )
        {
            final PrintStream currentStream = getOutputStreamForCurrentThread();
            synchronized ( currentStream )
            {
                currentStream.print( s );
                currentStream.notifyAll();
            }
        }
