    @Override
    public String getFormattedMessage() {
        if (formattedMessage == null) {
            if (message == null) {
                message = getMessage(messagePattern, argArray, throwable);
            }
            formattedMessage = message.getFormattedMessage();
        }
        return formattedMessage;
    }
