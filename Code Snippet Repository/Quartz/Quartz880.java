    private void rebind() throws NamingException, SchedulerException {
        InitialContext rootCtx = null;
        try {
            rootCtx = new InitialContext();
            Name fullName = rootCtx.getNameParser("").parse(jndiName);
            Scheduler scheduler = schedulerFactory.getScheduler();
            NonSerializableFactory.rebind(fullName, scheduler, true);
        } finally {
            if (rootCtx != null) { 
                try { 
                    rootCtx.close(); 
                } catch (NamingException ignore) {} 
            }
        }
    }
