    public void addModule(Module module)
    {
        if (!module.hasLicense())
        {
            // skip, no license
            return;
        }

        if (licenseMap.containsKey(module.getName()))
        {
            // skip, already being tracked
            return;
        }

        licenseMap.put(module.getName(),module.getLicense());
    }
