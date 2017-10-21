    protected boolean login(Subject clientSubject, String credentials, 
                            String authMethod, MessageInfo messageInfo) 
    throws IOException, UnsupportedCallbackException
    {
        credentials = credentials.substring(credentials.indexOf(' ')+1);
        credentials = B64Code.decode(credentials, StandardCharsets.ISO_8859_1);
        int i = credentials.indexOf(':');
        String userName = credentials.substring(0,i);
        String password = credentials.substring(i+1);
        return login(clientSubject, userName, new Password(password), authMethod, messageInfo);
    }
