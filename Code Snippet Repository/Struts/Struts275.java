    protected void notifyDeveloperParameterException(Object action, String property, String message) {
        String developerNotification = "Unexpected Exception caught setting '" + property + "' on '" + action.getClass() + ": " + message;
        if (action instanceof TextProvider) {
            TextProvider tp = (TextProvider) action;
            developerNotification = tp.getText("devmode.notification",
                    "Developer Notification:\n{0}",
                    new String[]{ developerNotification }
            );
        }

        LOG.error(developerNotification);

        if (action instanceof ValidationAware) {
            // see https://issues.apache.org/jira/browse/WW-4066
            Collection<String> messages = ((ValidationAware) action).getActionMessages();
            messages.add(message);
            ((ValidationAware) action).setActionMessages(messages);
        }
    }
