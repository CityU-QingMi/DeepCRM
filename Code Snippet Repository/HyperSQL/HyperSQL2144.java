    private void updateAddColumn(HsqlName table) {

        // roles
        Iterator it = getRoles().iterator();

        while (it.hasNext()) {
            Grantee grantee = (Grantee) it.next();

            grantee.updateRightsForNewColumn(table);
        }

        // users
        it = getGrantees().iterator();

        for (; it.hasNext(); ) {
            Grantee grantee = (Grantee) it.next();

            grantee.updateRightsForNewColumn(table);
        }
    }
