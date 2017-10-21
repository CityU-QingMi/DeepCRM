        @Override
        public void run()
        {
            // If the other peer closes the connection, the first
            // flush() should generate a TCP reset that is detected
            // on the second flush()
            try
            {
                synchronized (this)
                {
                    output.write('\r');
                    flush();
                    output.write('\n');
                    flush();
                }
                // We could write, reschedule heartbeat
                scheduleHeartBeat();
            }
            catch (IOException x)
            {
                // The other peer closed the connection
                close();
                eventSource.onClose();
            }
        }
