        @Override
        public void print( int i )
        {
            final PrintStream currentStream = getOutputStreamForCurrentThread();
            synchronized ( currentStream )
            {
                currentStream.print( i );
                currentStream.notifyAll();
            }
        }
