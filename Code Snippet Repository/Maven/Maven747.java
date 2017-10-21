        @Override
        public void print( boolean b )
        {
            final PrintStream currentStream = getOutputStreamForCurrentThread();
            synchronized ( currentStream )
            {
                currentStream.print( b );
                currentStream.notifyAll();
            }
        }
