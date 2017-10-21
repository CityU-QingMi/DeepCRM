    @com.google.inject.Inject
    protected JPAPersistenceStrategy(DatabaseProvider dbProvider) throws WebloggerException {
        String jpaConfigurationType = WebloggerConfig.getProperty("jpa.configurationType");
        if ("jndi".equals(jpaConfigurationType)) {
            // Lookup EMF via JNDI: added for Geronimo
            String emfJndiName = "java:comp/env/" + WebloggerConfig.getProperty("jpa.emf.jndi.name");
            try {
                emf = (EntityManagerFactory) new InitialContext().lookup(emfJndiName);
            } catch (NamingException e) {
                throw new WebloggerException("Could not look up EntityManagerFactory in jndi at " + emfJndiName, e);
            }
        } else {

            // Add all JPA, OpenJPA, HibernateJPA, etc. properties found
            Properties emfProps = new Properties();
            Enumeration keys = WebloggerConfig.keys();
            while (keys.hasMoreElements()) {
                String key = (String) keys.nextElement();
                if (       key.startsWith("javax.persistence.") 
                        || key.startsWith("openjpa.")
                        || key.startsWith("eclipselink.")
                        || key.startsWith("hibernate.")) {
                    String value = WebloggerConfig.getProperty(key);
                    logger.info(key + ": " + value);
                    emfProps.setProperty(key, value);
                }
            }

            if (dbProvider.getType() == DatabaseProvider.ConfigurationType.JNDI_NAME) {
                emfProps.setProperty("javax.persistence.nonJtaDataSource", dbProvider.getFullJndiName());
            } else {
                emfProps.setProperty("javax.persistence.jdbc.driver", dbProvider.getJdbcDriverClass());
                emfProps.setProperty("javax.persistence.jdbc.url", dbProvider.getJdbcConnectionURL());
                emfProps.setProperty("javax.persistence.jdbc.user", dbProvider.getJdbcUsername());
                emfProps.setProperty("javax.persistence.jdbc.password", dbProvider.getJdbcPassword());
            }

            try {
                this.emf = Persistence.createEntityManagerFactory("RollerPU", emfProps);

            } catch (Exception pe) {
                logger.error("ERROR: creating entity manager", pe);
                throw new WebloggerException(pe);
            }
        }
    }
