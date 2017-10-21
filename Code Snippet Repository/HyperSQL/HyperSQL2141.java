    public void checkRoleList(String granteeName, OrderedHashSet roleList,
                              Grantee grantor, boolean grant) {

        Grantee grantee = get(granteeName);

        for (int i = 0; i < roleList.size(); i++) {
            String  roleName = (String) roleList.get(i);
            Grantee role     = getRole(roleName);

            if (role == null) {
                throw Error.error(ErrorCode.X_0P000, roleName);
            }

            if (roleName.equals(SqlInvariants.SYSTEM_AUTHORIZATION_NAME)
                    || roleName.equals(SqlInvariants.PUBLIC_ROLE_NAME)) {
                throw Error.error(ErrorCode.X_28502, roleName);
            }

            if (grant) {
                if (grantee.getDirectRoles().contains(role)) {

                    /** @todo  shouldn't throw */
                    throw Error.error(ErrorCode.X_0P000, granteeName);
                }
            } else {
                if (!grantee.getDirectRoles().contains(role)) {

                    /** @todo  shouldn't throw */
                    throw Error.error(ErrorCode.X_0P000, roleName);
                }
            }

            if (!grantor.isAdmin()) {
                throw Error.error(ErrorCode.X_0L000,
                                  grantor.getName().getNameString());
            }
        }
    }
