        @Override
        public void write( byte b[], int off, int len )
        {
            final PrintStream currentStream = getOutputStreamForCurrentThread();
            synchronized ( currentStream )
            {
                currentStream.write( b, off, len );
                currentStream.notifyAll();
            }
        }
