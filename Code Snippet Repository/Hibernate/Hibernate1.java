    @Override
    public void start() {
        final JndiService jndiService = serviceRegistry
            .getService( JndiService.class );
        final ConnectionFactory jmsConnectionFactory = jndiService
            .locate( jmsConnectionFactoryName );

        this.jmsConnection = jmsConnectionFactory.createConnection();
        this.jmsSession = jmsConnection.createSession(
            true,
            Session.AUTO_ACKNOWLEDGE
        );

        final Destination destination = jndiService.locate( destinationName );

        this.publisher = jmsSession.createProducer( destination );
    }
