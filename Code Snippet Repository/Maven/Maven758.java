        @Override
        public void write( byte b[] )
            throws IOException
        {
            final PrintStream currentStream = getOutputStreamForCurrentThread();
            synchronized ( currentStream )
            {
                currentStream.write( b );
                currentStream.notifyAll();
            }
        }
