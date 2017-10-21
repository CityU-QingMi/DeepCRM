    private boolean tryLogin(MessageInfo messageInfo, Subject clientSubject, 
                             HttpServletResponse response, HttpSession session, 
                             String username, Password password) 
    throws AuthException, IOException, UnsupportedCallbackException
    {
        if (login(clientSubject, username, password, Constraint.__FORM_AUTH, messageInfo))
        {
            char[] pwdChars = password.toString().toCharArray();
            Set<LoginCallbackImpl> loginCallbacks = clientSubject.getPrivateCredentials(LoginCallbackImpl.class);
           
            if (!loginCallbacks.isEmpty())
            {
                LoginCallbackImpl loginCallback = loginCallbacks.iterator().next();
                Set<UserIdentity> userIdentities = clientSubject.getPrivateCredentials(UserIdentity.class);
                if (!userIdentities.isEmpty())
                {
                    UserIdentity userIdentity = userIdentities.iterator().next();
                   
                SessionAuthentication sessionAuth = new SessionAuthentication(Constraint.__FORM_AUTH, userIdentity, password);
                session.setAttribute(SessionAuthentication.__J_AUTHENTICATED, sessionAuth);
                }
            }

            return true;
        }
        return false;
    }
