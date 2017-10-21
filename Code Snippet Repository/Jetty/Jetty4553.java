    public void expandModules(List<Module> activeModules) throws IOException
    {
        StartLog.debug("Expanding Modules");
        for (Module module : activeModules)
        {
            // Find and Expand Libraries
            for (String rawlibref : module.getLibs())
            {
                StartLog.debug("rawlibref = " + rawlibref);
                String libref = properties.expand(rawlibref);
                StartLog.debug("expanded = " + libref);

                for (Path libpath : baseHome.getPaths(libref))
                {
                    classpath.addComponent(libpath.toFile());
                }
            }

            for (String jvmArg : module.getJvmArgs())
            {
                exec = true;
                jvmArgs.add(jvmArg);
            }

            // Find and Expand XML files
            for (String xmlRef : module.getXmls())
            {
                // Straight Reference
                xmlRef = properties.expand(xmlRef);
                Path xmlfile = baseHome.getPath(xmlRef);
                addUniqueXmlFile(xmlRef,xmlfile);
            }

            // Register Download operations
            for (String file : module.getFiles())
            {
                StartLog.debug("Adding module specified file: %s",file);
                addFile(module,file);
            }
        }
    }
