    public void initLogFile(Path logfile) throws IOException
    {
        if (logfile != null)
        {
            try
            {
                Path logDir = logfile.getParent();
                FS.ensureDirectoryWritable(logDir);

                Path startLog = logfile;

                if (!FS.exists(startLog) && !FS.createNewFile(startLog))
                {
                    // Output about error is lost in majority of cases.
                    throw new UsageException(UsageException.ERR_LOGGING,new IOException("Unable to create: " + startLog.toAbsolutePath()));
                }

                if (!FS.canWrite(startLog))
                {
                    // Output about error is lost in majority of cases.
                    throw new UsageException(UsageException.ERR_LOGGING,new IOException("Unable to write to: " + startLog.toAbsolutePath()));
                }

                err.println("StartLog to " + logfile);
                OutputStream fileout = Files.newOutputStream(startLog,StandardOpenOption.CREATE,StandardOpenOption.APPEND);
                PrintStream logger = new PrintStream(fileout,true);
                out=logger;
                err=logger;
                setStream(logger);
                System.setErr(logger);
                System.setOut(logger);
                err.println("StartLog Establishing " + logfile + " on " + new Date());
            }
            catch (IOException e)
            {
                throw new UsageException(UsageException.ERR_LOGGING,e);
            }
        }
    }
