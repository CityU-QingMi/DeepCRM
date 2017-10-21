    protected void setIdentityColumn(Session session, Object[] data) {

        if (identityColumn != -1) {
            Number id = (Number) data[identityColumn];

            if (identitySequence.getName() == null) {
                if (id == null) {
                    id = (Number) identitySequence.getValueObject();
                    data[identityColumn] = id;
                } else {
                    identitySequence.userUpdate(id.longValue());
                }
            } else {
                if (id == null) {
                    id = (Number) session.sessionData.getSequenceValue(
                        identitySequence);
                    data[identityColumn] = id;
                }
            }

            if (session != null) {
                session.setLastIdentity(id);
            }
        }
    }
