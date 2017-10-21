    @Override
    public String getFormattedMessage() {
        if (formattedMessage != null) {
            return formattedMessage;
        }
        ResourceBundle bundle = this.resourceBundle;
        if (bundle == null) {
            if (baseName != null) {
                bundle = getResourceBundle(baseName, locale, false);
            } else {
                bundle = getResourceBundle(loggerName, locale, true);
            }
        }
        final String myKey = getFormat();
        final String msgPattern = (bundle == null || !bundle.containsKey(myKey)) ? myKey : bundle.getString(myKey);
        final Object[] array = argArray == null ? stringArgs : argArray;
        final FormattedMessage msg = new FormattedMessage(msgPattern, array);
        formattedMessage = msg.getFormattedMessage();
        throwable = msg.getThrowable();
        return formattedMessage;
    }
