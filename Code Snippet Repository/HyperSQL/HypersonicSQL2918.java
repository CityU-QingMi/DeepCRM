    public String[] getRightsSQL() {

        HsqlArrayList list     = new HsqlArrayList();
        Iterator      grantees = getGrantees().iterator();

        while (grantees.hasNext()) {
            Grantee grantee = (Grantee) grantees.next();
            String  name    = grantee.getName().getNameString();

            // _SYSTEM user, DBA Role grants not persisted
            if (GranteeManager.isImmutable(name)) {
                continue;
            }

            if (grantee instanceof User && ((User) grantee).isExternalOnly) {
                continue;
            }

            HsqlArrayList subList = grantee.getRightsSQL();

            list.addAll(subList);
        }

        String[] array = new String[list.size()];

        list.toArray(array);

        return array;
    }
