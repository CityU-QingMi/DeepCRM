    public void dumpEnabled()
    {
        int i=0;
        List<Module> enabled = getEnabled();
        for (Module module:enabled)
        {
            String name=module.getName();
            String index=(i++)+")";
            for (String s:module.getEnableSources())
            {
                System.out.printf("  %4s %-15s %s%n",index,name,s);
                index="";
                name="";
            }
            if (module.isTransitive() && module.hasIniTemplate())
                System.out.printf("                       init template available with --add-to-start=%s%n",module.getName());
        }
    }
