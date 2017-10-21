    public void systemUpdateIdentityValue(Object[] data) {

        if (identityColumn != -1) {
            Number id = (Number) data[identityColumn];

            if (id != null) {
                identitySequence.systemUpdate(id.longValue());
            }
        }
    }
