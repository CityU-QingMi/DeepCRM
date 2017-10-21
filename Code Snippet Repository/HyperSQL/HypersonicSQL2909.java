    public void removeDbObjects(OrderedHashSet nameSet) {

        Iterator it = nameSet.iterator();

        while (it.hasNext()) {
            HsqlName name = (HsqlName) it.next();

            for (int i = 0; i < map.size(); i++) {
                Grantee g = (Grantee) map.get(i);

                g.revokeDbObject(name);
            }
        }
    }
