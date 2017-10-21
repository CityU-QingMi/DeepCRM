    protected PgType(Type hType, int oid, Integer dummy, long lpConstraint)
    throws RecoverableOdbcFailure {
        this(hType, oid, dummy, Integer.valueOf((int) lpConstraint));
        if (lpConstraint < 0) {
            throw new RecoverableOdbcFailure(
                "Length/Precision value is below minimum value of 0");
        }
        if (lpConstraint > Integer.MAX_VALUE) {
            throw new RecoverableOdbcFailure(
                "Length/Precision value is above maximum value of "
                + Integer.MAX_VALUE);
        }
    }
