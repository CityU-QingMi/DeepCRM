    public void addUser( String username, Credential credential, String[] roles)
    {
        Principal userPrincipal = new AbstractLoginService.UserPrincipal( username, credential);
        Subject subject = new Subject();
        subject.getPrincipals().add(userPrincipal);
        subject.getPrivateCredentials().add(credential);

        if (roles != null)
        {
            for (String role : roles)
            {
                subject.getPrincipals().add(new AbstractLoginService.RolePrincipal(role));
            }
        }

        subject.setReadOnly();
        _knownUserIdentities.put(username,_identityService.newUserIdentity(subject,userPrincipal,roles));
    }
