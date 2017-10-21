        @Override
        public void run()
        {
            try
            {
                synchronized(RolloverFileOutputStream.class)
                {
                    ZonedDateTime now = ZonedDateTime.now(_fileDateFormat.getTimeZone().toZoneId());
                    RolloverFileOutputStream.this.setFile(now);
                    RolloverFileOutputStream.this.scheduleNextRollover(now);
                    RolloverFileOutputStream.this.removeOldFiles(now);
                }
            }
            catch(Throwable t)
            {
                // Cannot log this exception to a LOG, as RolloverFOS can be used by logging
                t.printStackTrace(System.err);
            }
        }
