    private OrderedHashSet addGranteeAndRoles(OrderedHashSet set) {

        Grantee candidateRole;

        set.add(this);

        for (int i = 0; i < roles.size(); i++) {
            candidateRole = (Grantee) roles.get(i);

            if (!set.contains(candidateRole)) {
                candidateRole.addGranteeAndRoles(set);
            }
        }

        return set;
    }
