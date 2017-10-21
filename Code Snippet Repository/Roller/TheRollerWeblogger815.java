    public static void sendUserActivationEmail(User user)
            throws WebloggerException {
        
        Session mailSession = WebloggerStartup.getMailProvider() != null
                ? WebloggerStartup.getMailProvider().getSession() : null;

        if(mailSession == null) {
            throw new WebloggerException("ERROR: Notification email(s) not sent, "
                    + "Roller's mail session not properly configured");
        }
        
        try {
            ResourceBundle resources = ResourceBundle.getBundle(
                    "ApplicationResources", I18nUtils.toLocale(user.getLocale()));
            
            String from = WebloggerRuntimeConfig.getProperty(
                    "user.account.activation.mail.from");
            
            String cc[] = new String[0];
            String bcc[] = new String[0];
            String to[] = new String[] { user.getEmailAddress() };
            String subject = resources.getString(
                    "user.account.activation.mail.subject");
            String content;
            
            String rootURL = WebloggerRuntimeConfig.getAbsoluteContextURL();
            
            StringBuilder sb = new StringBuilder();
            
            // activationURL=
            String activationURL = rootURL
                    + "/roller-ui/register!activate.rol?activationCode="
                    + user.getActivationCode();
            sb.append(MessageFormat.format(
                    resources.getString("user.account.activation.mail.content"),
                    new Object[] { user.getFullName(), user.getUserName(),
                    activationURL }));
            content = sb.toString();
            
            sendHTMLMessage(from, to, cc, bcc, subject, content);
        } catch (MessagingException e) {
            throw new WebloggerException("ERROR: Problem sending activation email.", e);
        }
    }
