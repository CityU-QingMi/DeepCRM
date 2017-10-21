    public Reference getReference() throws NamingException {
        String    cname = "org.hsqldb.jdbc.JDBCDataSourceFactory";
        Reference ref   = new Reference(getClass().getName(), cname, null);

        ref.add(new StringRefAddr("database", source.getDatabase()));
        ref.add(new StringRefAddr("user", source.getUser()));
        ref.add(new StringRefAddr("password", source.password));
        ref.add(new StringRefAddr("loginTimeout",
                                  Integer.toString(source.loginTimeout)));
        ref.add(new StringRefAddr("poolSize", Integer.toString(connections.length)));

        return ref;
    }
