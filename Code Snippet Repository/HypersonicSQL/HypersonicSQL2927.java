    public void updateAddColumn(HsqlName table, HsqlName column) {

        // roles
        Iterator it = getRoles().iterator();

        while (it.hasNext()) {
            Grantee grantee = (Grantee) it.next();

            grantee.updateRightsForNewColumn(table, column);
        }

        // users
        it = getGrantees().iterator();

        for (; it.hasNext(); ) {
            Grantee grantee = (Grantee) it.next();

            grantee.updateRightsForNewColumn(table, column);
        }

        updateAddColumn(table);
    }
