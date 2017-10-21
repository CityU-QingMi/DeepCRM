        @Override
        public void print( float f )
        {
            final PrintStream currentStream = getOutputStreamForCurrentThread();
            synchronized ( currentStream )
            {
                currentStream.print( f );
                currentStream.notifyAll();
            }
        }
