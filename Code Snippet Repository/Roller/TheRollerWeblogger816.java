    public static void sendEmailApprovalNotifications(List<WeblogEntryComment> comments,
                                               I18nMessages resources) 
            throws MailingException {
        
        RollerMessages messages = new RollerMessages();
        for (WeblogEntryComment comment : comments) {

            // Send email notifications because a new comment has been approved
            sendEmailNotification(comment, messages, resources, true);

            // Send approval notification to author of approved comment
            sendEmailApprovalNotification(comment, resources);
        }
    }
