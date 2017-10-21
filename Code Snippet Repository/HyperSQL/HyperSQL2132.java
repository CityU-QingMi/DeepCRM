    public String[] getSQL() {

        HsqlArrayList list = new HsqlArrayList();

        // roles
        Iterator it = getRoles().iterator();

        while (it.hasNext()) {
            Grantee grantee = (Grantee) it.next();

            // ADMIN_ROLE_NAME is not persisted
            if (!GranteeManager.isReserved(
                    grantee.getName().getNameString())) {
                list.add(grantee.getSQL());
            }
        }

        // users
        it = getGrantees().iterator();

        for (; it.hasNext(); ) {
            Grantee grantee = (Grantee) it.next();

            if (grantee instanceof User) {
                if (((User) grantee).isExternalOnly) {
                    continue;
                }

                list.add(grantee.getSQL());

                if (((User) grantee).isLocalOnly) {
                    list.add(((User) grantee).getLocalUserSQL());
                }
            }
        }

        String[] array = new String[list.size()];

        list.toArray(array);

        return array;
    }
