    public void listModules(StartArgs args)
    {
        List<String> tags = args.getListModules();
        
        StartLog.endStartLog();
        System.out.println();
        System.out.println("Available Modules:");
        System.out.println("==================");
        System.out.println("tags: "+tags);
        args.getAllModules().dump(tags);

        // Dump Enabled Modules
        System.out.println();
        System.out.println("Enabled Modules:");
        System.out.println("================");
        Modules modules = args.getAllModules();
        modules.dumpEnabled();
    }
