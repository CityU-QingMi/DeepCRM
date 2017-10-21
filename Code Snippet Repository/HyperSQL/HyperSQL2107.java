    boolean updateNestedRoles(Grantee role) {

        boolean hasNested = false;

        if (role != this) {
            for (int i = 0; i < roles.size(); i++) {
                Grantee currentRole = (Grantee) roles.get(i);

                hasNested |= currentRole.updateNestedRoles(role);
            }
        }

        if (hasNested) {
            updateAllRights();
        }

        return hasNested || role == this;
    }
