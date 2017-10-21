    public void dumpEnvironment()
    {
        // Java Details
        System.out.println();
        System.out.println("Java Environment:");
        System.out.println("-----------------");
        dumpSystemProperty("java.home");
        dumpSystemProperty("java.vm.vendor");
        dumpSystemProperty("java.vm.version");
        dumpSystemProperty("java.vm.name");
        dumpSystemProperty("java.vm.info");
        dumpSystemProperty("java.runtime.name");
        dumpSystemProperty("java.runtime.version");
        dumpSystemProperty("java.io.tmpdir");
        dumpSystemProperty("user.dir");
        dumpSystemProperty("user.language");
        dumpSystemProperty("user.country");

        // Jetty Environment
        System.out.println();
        System.out.println("Jetty Environment:");
        System.out.println("-----------------");
        dumpProperty("jetty.version");
        dumpProperty("jetty.tag.version");
        dumpProperty("jetty.home");
        dumpProperty("jetty.base");

        // Jetty Configuration Environment
        System.out.println();
        System.out.println("Config Search Order:");
        System.out.println("--------------------");
        for (ConfigSource config : baseHome.getConfigSources())
        {
            System.out.printf(" %s",config.getId());
            if (config instanceof DirConfigSource)
            {
                DirConfigSource dirsource = (DirConfigSource)config;
                if (dirsource.isPropertyBased())
                {
                    System.out.printf(" -> %s",dirsource.getDir());
                }
            }
            System.out.println();
        }

        // Jetty Se
        System.out.println();
    }
