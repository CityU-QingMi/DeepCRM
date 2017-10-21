    public void systemSetIdentityColumn(Session session, Object[] data) {

        if (identityColumn != -1) {
            Number id = (Number) data[identityColumn];

            if (id == null) {
                id = (Number) identitySequence.getValueObject();
                data[identityColumn] = id;
            } else {
                identitySequence.userUpdate(id.longValue());
            }
        }
    }
