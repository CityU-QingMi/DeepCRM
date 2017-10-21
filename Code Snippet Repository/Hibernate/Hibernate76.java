    @Override
    public String objectToSQLString(
        Date value,
        Dialect dialect) throws Exception {
        final Timestamp ts = Timestamp.class.isInstance( value )
            ? ( Timestamp ) value
            : new Timestamp( value.getTime() );
        return StringType.INSTANCE.objectToSQLString(
            ts.toString(), dialect
        );
    }
