        @Override
        public void println( boolean x )
        {
            final PrintStream currentStream = getOutputStreamForCurrentThread();
            synchronized ( currentStream )
            {
                currentStream.print( x );
                currentStream.notifyAll();
            }
        }
