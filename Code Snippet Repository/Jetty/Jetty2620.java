    @Override
    public UserIdentity login(String username, Object credentials, ServletRequest request)
    {
        if (username == null)
            return null;

        UserPrincipal userPrincipal = loadUserInfo(username);
        if (userPrincipal != null && userPrincipal.authenticate(credentials))
        {
            //safe to load the roles
            String[] roles = loadRoleInfo(userPrincipal);
                       
            Subject subject = new Subject();
            subject.getPrincipals().add(userPrincipal);
            subject.getPrivateCredentials().add(userPrincipal._credential);
            if (roles!=null)
                for (String role : roles)
                    subject.getPrincipals().add(new RolePrincipal(role));
            subject.setReadOnly();
            return _identityService.newUserIdentity(subject,userPrincipal,roles);
        }

        return null;

    }
