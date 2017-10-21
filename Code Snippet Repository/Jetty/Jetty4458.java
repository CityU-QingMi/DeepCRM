    private boolean processFileResources(List<FileArg> files) throws IOException
    {
        if ((files == null) || (files.isEmpty()))
        {
            return false;
        }

        boolean dirty = false;

        List<String> failures = new ArrayList<String>();

        for (FileArg arg : files)
        {
            try
            {
                boolean processed = processFileResource(arg);
                dirty |= processed;
            }
            catch (Throwable t)
            {
                StartLog.warn(t);
                failures.add(String.format("[%s] %s - %s",t.getClass().getSimpleName(),t.getMessage(),arg.location));
            }
        }

        if (!failures.isEmpty())
        {
            StringBuilder err = new StringBuilder();
            err.append("Failed to process all file resources.");
            for (String failure : failures)
            {
                err.append(System.lineSeparator()).append(" - ").append(failure);
            }
            StartLog.warn(err.toString());

            throw new RuntimeException(err.toString());
        }

        return dirty;
    }
