    public Collection<GrantedAuthority> getGrantedAuthorities(DirContextOperations userData, String username) {

        // This check is probably unnecessary.
        if (userData == null) {
            throw new IllegalArgumentException("The userData argument should not be null at this point.");
        }

        User user;
        List<String> roles = new ArrayList<String>();
        try {
            Weblogger roller = WebloggerFactory.getWeblogger();
            UserManager umgr = roller.getUserManager();
            user = umgr.getUserByUserName(username, Boolean.TRUE);
            if (user != null) {
                roles = umgr.getRoles(user);
            }
        } catch (WebloggerException ex) {
            throw new DataRetrievalFailureException("ERROR in user lookup", ex);
        }

        int roleCount = roles.size() + (defaultRole != null ? 1 : 0);
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(roleCount);
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        
        if (defaultRole != null) {
            authorities.add(defaultRole);
        }

        if (authorities.size() == 0) {
            // TODO: This doesn't seem like the right type of exception to throw here, but retained it, fixed the message
            throw new UsernameNotFoundException("User " + username + " has no roles granted and there is no default role set.");
        }

        return authorities;
    }
