    private void insertRoles(Session session, Table t, Grantee role,
                             boolean isGrantable) {

        final int       grantee      = 0;
        final int       role_name    = 1;
        final int       is_grantable = 2;
        PersistentStore store        = t.getRowStore(session);

        if (isGrantable) {
            Set      roles = database.getGranteeManager().getRoleNames();
            Iterator it    = roles.iterator();

            while (it.hasNext()) {
                String   roleName = (String) it.next();
                Object[] row      = t.getEmptyRowData();

                row[grantee]      = role.getName().getNameString();
                row[role_name]    = roleName;
                row[is_grantable] = Tokens.T_YES;

                t.insertSys(session, store, row);
            }
        } else {
            OrderedHashSet roles = role.getDirectRoles();

            for (int i = 0; i < roles.size(); i++) {
                Grantee  currentRole = (Grantee) roles.get(i);
                String   roleName    = currentRole.getName().getNameString();
                Object[] row         = t.getEmptyRowData();

                row[grantee]      = role.getName().getNameString();
                row[role_name]    = roleName;
                row[is_grantable] = Tokens.T_NO;

                t.insertSys(session, store, row);

                role = database.getGranteeManager().getRole(roleName);

                insertRoles(session, t, role, isGrantable);
            }
        }
    }
