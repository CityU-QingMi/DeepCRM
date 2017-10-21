    private static void findConfiguredThreadNames(Set<Object> seen, List<String> threadNames, ContainerLifeCycle container)
    {
        if (seen.contains(container))
        {
            // skip
            return;
        }
        
        seen.add(container);
        
        Collection<Executor> executors = container.getBeans(Executor.class);
        for (Executor executor : executors)
        {
            if (executor instanceof QueuedThreadPool)
            {
                QueuedThreadPool qtp = (QueuedThreadPool) executor;
                threadNames.add(qtp.getName());
            }
        }
        
        for (ContainerLifeCycle child : container.getBeans(ContainerLifeCycle.class))
        {
            findConfiguredThreadNames(seen, threadNames, child);
        }
    }
