        @SuppressWarnings("")
        @Override
        public JmsAppender build() {
            JmsManager actualJmsManager = jmsManager;
            JmsManagerConfiguration configuration = null;
            if (actualJmsManager == null) {
                final Properties jndiProperties = JndiManager.createProperties(factoryName, providerUrl, urlPkgPrefixes,
                        securityPrincipalName, securityCredentials, null);
                configuration = new JmsManagerConfiguration(jndiProperties, factoryBindingName, destinationBindingName,
                        userName, password, false, reconnectIntervalMillis);
                actualJmsManager = AbstractManager.getManager(name, JmsManager.FACTORY, configuration);
            }
            if (actualJmsManager == null) {
                // JmsManagerFactory has already logged an ERROR.
                return null;
            }
            if (layout == null) {
                LOGGER.error("No layout provided for JmsAppender");
                return null;
            }
            try {
                return new JmsAppender(name, filter, layout, ignoreExceptions, actualJmsManager);
            } catch (final JMSException e) {
                //  Never happens since the ctor no longer actually throws a JMSException.
                throw new IllegalStateException(e);
            }
        }
