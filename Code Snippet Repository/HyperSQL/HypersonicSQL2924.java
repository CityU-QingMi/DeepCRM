    public void grant(String granteeName, String roleName, Grantee grantor) {

        Grantee grantee = get(granteeName);

        if (grantee == null) {
            throw Error.error(ErrorCode.X_28501, granteeName);
        }

        if (isImmutable(granteeName)) {
            throw Error.error(ErrorCode.X_28502, granteeName);
        }

        Grantee role = getRole(roleName);

        if (role == null) {
            throw Error.error(ErrorCode.X_0P000, roleName);
        }

        if (role == grantee) {
            throw Error.error(ErrorCode.X_0P501, granteeName);
        }

        // campbell-burnet@users 20050515
        // SQL 2003 Foundation, 4.34.3
        // No cycles of role grants are allowed.
        if (role.hasRole(grantee)) {

            // campbell-burnet@users

            /** @todo: Correct reporting of actual grant path */
            throw Error.error(ErrorCode.X_0P501, roleName);
        }

        if (!grantor.isGrantable(role)) {
            throw Error.error(ErrorCode.X_0L000,
                              grantor.getName().getNameString());
        }

        grantee.grant(role);
        grantee.updateAllRights();

        if (grantee.isRole) {
            updateAllRights(grantee);
        }
    }
