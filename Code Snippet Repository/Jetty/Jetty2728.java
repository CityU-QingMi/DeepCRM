    @Override
    protected String[] loadRoleInfo(UserPrincipal user)
    {
        UserIdentity userIdentity = userStore.getUserIdentity( user.getName() );
        Set<RolePrincipal> roles = userIdentity.getSubject().getPrincipals( RolePrincipal.class);
        if (roles == null)
            return null;

        List<String> list = new ArrayList<>();
        for (RolePrincipal r:roles)
            list.add(r.getName());

        return list.toArray(new String[roles.size()]);
    }
