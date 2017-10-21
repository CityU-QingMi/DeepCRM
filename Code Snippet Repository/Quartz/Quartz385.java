    public void initialize(Logger logger, String tablePrefix, String schedName, String instanceId, ClassLoadHelper classLoadHelper, boolean useProperties, String initString) throws NoSuchDelegateException {

        this.logger = logger;
        this.tablePrefix = tablePrefix;
        this.schedName = schedName;
        this.instanceId = instanceId;
        this.useProperties = useProperties;
        this.classLoadHelper = classLoadHelper;
        addDefaultTriggerPersistenceDelegates();

        if(initString == null)
            return;

        String[] settings = initString.split("\\|");
        
        for(String setting: settings) {
            String[] parts = setting.split("=");
            String name = parts[0];
            if(parts.length == 1 || parts[1] == null || parts[1].equals(""))
                continue;

            if(name.equals("triggerPersistenceDelegateClasses")) {
                
                String[] trigDelegates = parts[1].split(",");
                
                for(String trigDelClassName: trigDelegates) {
                    try {
                        Class<?> trigDelClass = classLoadHelper.loadClass(trigDelClassName);
                        addTriggerPersistenceDelegate((TriggerPersistenceDelegate) trigDelClass.newInstance());
                    } catch (Exception e) {
                        throw new NoSuchDelegateException("Error instantiating TriggerPersistenceDelegate of type: " + trigDelClassName, e);
                    } 
                }
            }
            else
                throw new NoSuchDelegateException("Unknown setting: '" + name + "'");
        }
    }
