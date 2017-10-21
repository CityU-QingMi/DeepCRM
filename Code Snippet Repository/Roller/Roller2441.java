    public static I18nMessages getMessages(Locale locale) {
        
        LOG.debug("request for messages in locale = " + locale.toString());
        
        // check if we already have a message utils created for that locale
        I18nMessages messages = messagesMap.get(locale);
        
        // if no utils for that language yet then construct
        if(messages == null) {
            messages = new I18nMessages(locale);
            
            // keep a reference to it
            messagesMap.put(messages.getLocale(), messages);
        }
        
        return messages;
    }
