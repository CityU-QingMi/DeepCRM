    public String getText(String key, String defaultValue, String[] args) {
        // if there's one text provider that gives us a msg not the same as defaultValue
        // for this key, we are ok, else try the next
        // text provider
        for (TextProvider textProvider : textProviders) {
            String msg = textProvider.getText(key, defaultValue, args);
            if (msg != null && (!msg.equals(defaultValue))) {
                return msg;
            }
        }
        return defaultValue;
    }
