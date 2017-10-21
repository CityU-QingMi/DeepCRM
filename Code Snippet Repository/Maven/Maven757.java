        @Override
        public void write( int b )
        {
            final PrintStream currentStream = getOutputStreamForCurrentThread();
            synchronized ( currentStream )
            {
                currentStream.write( b );
                currentStream.notifyAll();
            }
        }
