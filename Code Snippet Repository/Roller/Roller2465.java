    public static void sendEmailApprovalNotification(WeblogEntryComment cd, I18nMessages resources)
            throws MailingException {
        
        WeblogEntry entry = cd.getWeblogEntry();
        Weblog weblog = entry.getWebsite();
        User user = entry.getCreator();
        
        // use either the weblog configured from address or the site configured from address
        String from = weblog.getEmailAddress();
        if(StringUtils.isEmpty(from)) {
            from = user.getEmailAddress();
        }
        
        // form the message to be sent
        String subject = resources.getString("email.comment.commentApproved");
        
        StringBuilder msg = new StringBuilder();
        msg.append(resources.getString("email.comment.commentApproved"));
        msg.append("\n\n");
        msg.append(WebloggerFactory.getWeblogger().getUrlStrategy()
            .getWeblogCommentsURL(weblog, null, entry.getAnchor(), true));
        
        // send message to author of approved comment
        try {
            sendTextMessage(from, new String[] {cd.getEmail()}, null, null, subject, msg.toString());
        } catch (Exception e) {
            log.warn("Exception sending comment mail: " + e.getMessage());
            // This will log the stack trace if debug is enabled
            if (log.isDebugEnabled()) {
                log.debug(e);
            }
        }
        
        log.debug("Done sending email message");
    }
