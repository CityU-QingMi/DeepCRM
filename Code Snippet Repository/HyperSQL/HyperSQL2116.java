    void revoke(SchemaObject object, Right right, Grantee grantor,
                boolean grantOption) {

        HsqlName name = object.getName();

        if (object instanceof Routine) {
            name = ((Routine) object).getSpecificName();
        }

        Iterator it       = directRightsMap.get(name);
        Right    existing = null;

        while (it.hasNext()) {
            existing = (Right) it.next();

            if (existing.grantor == grantor) {
                break;
            }
        }

        if (existing == null) {
            return;
        }

        if (existing.grantableRights != null) {
            existing.grantableRights.remove(object, right);
        }

        if (grantOption) {
            return;
        }

        if (right.isFull) {
            directRightsMap.remove(name, existing);
            grantor.grantedRightsMap.remove(name, existing);
            updateAllRights();

            return;
        }

        existing.remove(object, right);

        if (existing.isEmpty()) {
            directRightsMap.remove(name, existing);
            grantor.grantedRightsMap.remove(name, existing);
        }

        updateAllRights();
    }
