    protected synchronized void lazyInit() {
        if (execute == null) {
            InitOperations init = new InitOperations();
            Dispatcher dispatcher = init.findDispatcherOnThread();
            init.initStaticContentLoader(new FilterHostConfig(filterConfig), dispatcher);

            prepare = new PrepareOperations(dispatcher);
            execute = new ExecuteOperations(dispatcher);
        }

    }
