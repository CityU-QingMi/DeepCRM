    private JmsManager(final String name, final JmsManagerConfiguration configuration) {
        super(null, name);
        this.configuration = configuration;
        this.jndiManager = configuration.getJndiManager();
        try {
            this.connection = createConnection(this.jndiManager);
            this.session = createSession(this.connection);
            this.destination = createDestination(this.jndiManager);
            this.messageProducer = createMessageProducer(this.session, this.destination);
            this.connection.start();
        } catch (NamingException | JMSException e) {
            this.reconnector = createReconnector();
            this.reconnector.start();
        }
    }
