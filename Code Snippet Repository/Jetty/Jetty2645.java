    @Override
    protected String[] loadRoleInfo(UserPrincipal user)
    {
        UserIdentity id = _userStore.getUserIdentity(user.getName());
        if (id == null)
            return null;


        Set<RolePrincipal> roles = id.getSubject().getPrincipals(RolePrincipal.class);
        if (roles == null)
            return null;

        List<String> list = roles.stream()
            .map( rolePrincipal -> rolePrincipal.getName() )
            .collect(  Collectors.toList() );

        return list.toArray(new String[roles.size()]);
    }
