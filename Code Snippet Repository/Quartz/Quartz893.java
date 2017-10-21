    protected Session getMailSession(final MailInfo mailInfo) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", mailInfo.getSmtpHost());
        
        // pass along extra smtp settings from users
        Properties extraSettings = mailInfo.getSmtpProperties();
        if (extraSettings != null) {
            properties.putAll(extraSettings);
        }
        
        Authenticator authenticator = null;
        if (mailInfo.getUsername() != null && mailInfo.getPassword() != null) {
            log.info("using username '{}' and password 'xxx'", mailInfo.getUsername());
            authenticator = new Authenticator() { 
                protected PasswordAuthentication getPasswordAuthentication() { 
                    return new PasswordAuthentication(mailInfo.getUsername(), mailInfo.getPassword()); 
                }
            };
        }
        log.debug("Sending mail with properties: {}", properties);
        return Session.getDefaultInstance(properties, authenticator);
    }
