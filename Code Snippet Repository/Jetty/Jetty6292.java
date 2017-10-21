    public static List<String> getThreadNames(ContainerLifeCycle... containers)
    {
        List<String> threadNames = new ArrayList<>();
        Set<Object> seen = new HashSet<>();
        for (ContainerLifeCycle container : containers)
        {
            if (container == null)
            {
                continue;
            }
            
            findConfiguredThreadNames(seen, threadNames, container);
        }
        seen.clear();
        // System.out.println("Threads: " + threadNames.stream().collect(Collectors.joining(", ", "[", "]")));
        return threadNames;
    }
