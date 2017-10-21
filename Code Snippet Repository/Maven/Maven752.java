        @Override
        public void print( Object obj )
        {
            final PrintStream currentStream = getOutputStreamForCurrentThread();
            synchronized ( currentStream )
            {
                currentStream.print( obj );
                currentStream.notifyAll();
            }
        }
