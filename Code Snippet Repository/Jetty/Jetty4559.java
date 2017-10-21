    private void enableModules(String source, List<String> moduleNames)
    {
        for (String moduleName : moduleNames)
        {
            modules.add(moduleName);
            List<String> list = sources.get(moduleName);
            if (list == null)
            {
                list = new ArrayList<>();
                sources.put(moduleName,list);
            }
            list.add(source);
        }
    }
