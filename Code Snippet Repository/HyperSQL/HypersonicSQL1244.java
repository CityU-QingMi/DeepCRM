    public static java.sql.Array authenticate(
            String database, String user, String password)
            throws Exception {
/**/
/**/
/**/
/**/
        if (database == null || database.length() != 16) {
            throw new IllegalStateException("Internal problem.  "
                    + "Database name not exactly 16 characters long: "
                    + database);
        }
        List<AuthFunctionBean> beanList =
                AuthBeanMultiplexer.beans.get(database);
        if (beanList == null) {
            logger.error("Database '" + database
                    + "' has not been set up with "
                    + AuthBeanMultiplexer.class.getName());
            throw new IllegalArgumentException("Database '" + database
                    + "' has not been set up with "
                    + AuthBeanMultiplexer.class.getName());
        }
        Exception firstRTE = null;
        String[] beanRet;
        for (AuthFunctionBean nextBean : beanList) try {
            beanRet = nextBean.authenticate(user, password);
            return (beanRet == null)
                    ? null : new JDBCArrayBasic(beanRet, Type.SQL_VARCHAR);
        } catch (RuntimeException re) {
            if (firstRTE == null) {
                firstRTE = re;
            }
            logger.error("System failure of an AuthFunctionBean: "
                    + ((re.getMessage() == null)
                      ? re.toString() : re.getMessage()));
        } catch (Exception e) {
            throw e;
        }
        throw firstRTE;
    }
