        void reconnect() throws NamingException, JMSException {
            final JndiManager jndiManager2 = getJndiManager();
            final Connection connection2 = createConnection(jndiManager2);
            final Session session2 = createSession(connection2);
            final Destination destination2 = createDestination(jndiManager2);
            final MessageProducer messageProducer2 = createMessageProducer(session2, destination2);
            connection2.start();
            synchronized (owner) {
                jndiManager = jndiManager2;
                connection = connection2;
                session = session2;
                destination = destination2;
                messageProducer = messageProducer2;
                reconnector = null;
                shutdown = true;
            }
            LOGGER.debug("Connection reestablished to {}", configuration);
        }
