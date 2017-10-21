    public String[] authenticate(String userName, String password)
            throws DenyException {
        if (!initialized) {
            throw new IllegalStateException(
                "You must invoke the 'init' method to initialize the "
                + HsqldbSlaveAuthBean.class.getName() + " instance.");
        }
        Connection c = null;
        try {
            c = DriverManager.getConnection(masterJdbcUrl, userName, password);
            if (delegateRolesSchema) {
                Set<String> schemaAndRoles = AuthUtils.getEnabledRoles(c);
                String schemaOnMaster = AuthUtils.getInitialSchema(c);
                if (schemaOnMaster != null) {
                    schemaAndRoles.add(schemaOnMaster);
                }
                logger.finer("Slave delegating schema+roles: "
                        + schemaAndRoles);
                return schemaAndRoles.toArray(new String[0]);
            }
            return null;
        } catch (SQLException se) {
            throw new DenyException();
        } finally {
            if (c != null) try {
                c.close();
                c = null;  // Encourage GC
            } catch (SQLException nestedSe) {
                logger.severe(
                        "Failed to close master/slave Connection", nestedSe);
            }
        }
    }
