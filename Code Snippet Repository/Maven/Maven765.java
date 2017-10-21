        @Override
        public void print( double d )
        {
            final PrintStream currentStream = getOutputStreamForCurrentThread();
            synchronized ( currentStream )
            {
                currentStream.print( d );
                currentStream.notifyAll();
            }
        }
