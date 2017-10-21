    public void setAuthFunctionBeans(String dbName,
            List<AuthFunctionBean> authFunctionBeans) {
        if (dbName == null || dbName.length() != 16) {
            throw new IllegalArgumentException(
                    "Database name not exactly 16 characters long: "
                    + dbName);
        }
        List<AuthFunctionBean> dbsBeans = AuthBeanMultiplexer.beans.get(dbName);
        if (dbsBeans == null) {
            dbsBeans = new ArrayList<AuthFunctionBean>();
            AuthBeanMultiplexer.beans.put(dbName, dbsBeans);
        } else {
            if (dbsBeans.size() > 0)
                throw new IllegalStateException(
                        "Use setAuthFunctionBeans(String, List) only when the "
                        + "db's AuthFunctionBean list is empty");
        }
        dbsBeans.addAll(authFunctionBeans);
    }
