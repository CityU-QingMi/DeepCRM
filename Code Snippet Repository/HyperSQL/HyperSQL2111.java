    Right getAllGrantableRights(HsqlName name) {

        if (isAdmin) {
            return name.schema.owner.ownerRights;
        }

        if (name.schema.owner == this) {
            return ownerRights;
        }

        if (roles.contains(name.schema.owner)) {
            return name.schema.owner.ownerRights;
        }

        OrderedHashSet set = getAllRoles();

        for (int i = 0; i < set.size(); i++) {
            Grantee role = (Grantee) set.get(i);

            if (name.schema.owner == role) {
                return role.ownerRights;
            }
        }

        Right right = (Right) fullRightsMap.get(name);

        return right == null || right.grantableRights == null ? Right.noRights
                                                              : right
                                                              .grantableRights;
    }
