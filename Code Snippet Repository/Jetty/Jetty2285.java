    public void initDb() throws NamingException, SQLException
    {
        if (_datasource != null)
            return;

        @SuppressWarnings("unused")
        InitialContext ic = new InitialContext();
        assert ic!=null;

        // TODO Should we try webapp scope too?

        // try finding the datasource in the Server scope
        if (_server != null)
        {
            try
            {
                _datasource = (DataSource)NamingEntryUtil.lookup(_server, _jndiName);
            }
            catch (NameNotFoundException e)
            {
                //next try the jvm scope
            }
        }


        //try finding the datasource in the jvm scope
        if (_datasource==null)
        {
            _datasource = (DataSource)NamingEntryUtil.lookup(null, _jndiName);
        }

        // set up the select statements based on the table and column names configured
        _userSql = "select " + _userTableKey + "," + _userTablePasswordField
                  + " from " + _userTableName
                  + " where "+ _userTableUserField + " = ?";

        _roleSql = "select r." + _roleTableRoleField
                  + " from " + _roleTableName + " r, " + _userRoleTableName
                  + " u where u."+ _userRoleTableUserKey + " = ?"
                  + " and r." + _roleTableKey + " = u." + _userRoleTableRoleKey;

        prepareTables();
    }
