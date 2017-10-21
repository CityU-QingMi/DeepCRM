    public MailProvider() throws StartupException {
        
        String connectionTypeString = WebloggerConfig.getProperty("mail.configurationType"); 
        if ("properties".equals(connectionTypeString)) {
            type = ConfigurationType.MAIL_PROPERTIES;
        }
        String jndiName =     WebloggerConfig.getProperty("mail.jndi.name");
        mailHostname = WebloggerConfig.getProperty("mail.hostname");
        mailUsername = WebloggerConfig.getProperty("mail.username");
        mailPassword = WebloggerConfig.getProperty("mail.password");
        try {
            String portString = WebloggerConfig.getProperty("mail.port");
            if (portString != null) {
                mailPort = Integer.parseInt(portString);
            }
        } catch (Exception e) {
            LOG.warn("mail server port not a valid integer, ignoring");
        }
        
        // init and connect now so we fail early
        if (type == ConfigurationType.JNDI_NAME) {            
            if (jndiName != null && !jndiName.startsWith("java:")) {
                jndiName = "java:comp/env/" + jndiName;
            }
            try {
                Context ctx = new InitialContext();
                session = (Session) ctx.lookup(jndiName);
            } catch (NamingException ex) {
                throw new StartupException("ERROR looking up mail-session with JNDI name: " + jndiName);
            }
        } else {
            Properties props = new Properties();
            props.put("mail.smtp.host", mailHostname);
            if (mailUsername != null && mailPassword != null) {
                props.put("mail.smtp.auth", "true");   
            }
            if (mailPort != -1) {
                props.put("mail.smtp.port", ""+mailPort);
            }
            session = Session.getDefaultInstance(props, null);
        }
        
        try {
            Transport transport = getTransport();
            transport.close();
        } catch (Exception e) {
            throw new StartupException("ERROR connecting to mail server", e);
        }
        
    }
