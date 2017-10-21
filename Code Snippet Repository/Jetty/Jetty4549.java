    private void addFile(Module module, String uriLocation)
    {
        if (module.isSkipFilesValidation())
        {
            StartLog.debug("Not validating %s [files] for %s",module,uriLocation);
            return;
        }

        FileArg arg = new FileArg(module,properties.expand(uriLocation));
        if (!files.contains(arg))
        {
            files.add(arg);
        }
    }
