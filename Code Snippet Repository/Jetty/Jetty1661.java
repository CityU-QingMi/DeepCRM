    public ManagedSelector(SelectorManager selectorManager, int id)
    {
        _selectorManager = selectorManager;
        _id = id;
        SelectorProducer producer = new SelectorProducer();
        Executor executor = selectorManager.getExecutor();
        _strategy = new EatWhatYouKill(producer,executor,_selectorManager.getBean(ReservedThreadExecutor.class));            
        addBean(_strategy,true);
        setStopTimeout(5000);
    }
