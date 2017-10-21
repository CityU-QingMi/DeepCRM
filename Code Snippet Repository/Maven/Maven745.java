        @Override
        public void print( long l )
        {
            final PrintStream currentStream = getOutputStreamForCurrentThread();
            synchronized ( currentStream )
            {
                currentStream.print( l );
                currentStream.notifyAll();
            }
        }
