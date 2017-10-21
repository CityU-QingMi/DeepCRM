    private void updateFileOverTime(Path path, int timeDuration, TimeUnit timeUnit)
    {
        try
        {
            // how long to sleep between writes
            int sleepMs = 200;

            // average chunk buffer
            int chunkBufLen = 16;
            byte chunkBuf[] = new byte[chunkBufLen];
            Arrays.fill(chunkBuf, (byte)'x');
            long end = System.nanoTime() + timeUnit.toNanos(timeDuration);
            
            try (FileOutputStream out = new FileOutputStream(path.toFile()))
            {
                while(System.nanoTime()<end)
                {
                    out.write(chunkBuf);
                    out.flush();
                    out.getChannel().force(true);
                    // Force file to actually write to disk.
                    // Skipping any sort of filesystem caching of the write
                    out.getFD().sync();
                    TimeUnit.MILLISECONDS.sleep(sleepMs);
                }
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
