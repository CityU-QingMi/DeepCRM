        @Override
        public void println()
        {
            final PrintStream currentStream = getOutputStreamForCurrentThread();
            synchronized ( currentStream )
            {
                currentStream.println();
                currentStream.notifyAll();
            }
        }
