    public OrderedHashSet visibleGrantees() {

        OrderedHashSet grantees = new OrderedHashSet();
        GranteeManager gm       = granteeManager;

        if (isAdmin()) {
            grantees.addAll(gm.getGrantees());
        } else {
            grantees.add(this);

            Iterator it = getAllRoles().iterator();

            while (it.hasNext()) {
                grantees.add(it.next());
            }
        }

        return grantees;
    }
