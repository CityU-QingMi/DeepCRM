    private void addToFullRights(MultiValueHashMap map) {

        Iterator it = map.keySet().iterator();

        while (it.hasNext()) {
            Object   key      = it.next();
            Iterator values   = map.get(key);
            Right    existing = (Right) fullRightsMap.get(key);

            while (values.hasNext()) {
                Right add = (Right) values.next();

                if (existing == null) {
                    existing = add.duplicate();

                    fullRightsMap.put(key, existing);
                } else {
                    existing.add(add);
                }

                if (add.grantableRights == null) {
                    continue;
                }

                if (existing.grantableRights == null) {
                    existing.grantableRights = add.grantableRights.duplicate();
                } else {
                    existing.grantableRights.add(add.grantableRights);
                }
            }
        }
    }
