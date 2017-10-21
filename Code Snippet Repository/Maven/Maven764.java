        @Override
        public void println( char x )
        {
            final PrintStream currentStream = getOutputStreamForCurrentThread();
            synchronized ( currentStream )
            {
                currentStream.println( x );
                currentStream.notifyAll();
            }
        }
