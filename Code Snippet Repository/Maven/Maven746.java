        @Override
        public void println( long x )
        {
            final PrintStream currentStream = getOutputStreamForCurrentThread();
            synchronized ( currentStream )
            {
                currentStream.print( x );
                currentStream.notifyAll();
            }
        }
