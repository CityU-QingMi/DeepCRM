    public void initialize ()
    throws Exception
    {
        if (_datasource != null)
            return; //already set up
        
        if (_jndiName!=null)
        {
            InitialContext ic = new InitialContext();
            _datasource = (DataSource)ic.lookup(_jndiName);
        }
        else if ( _driver != null && _connectionUrl != null )
        {
            DriverManager.registerDriver(_driver);
        }
        else if (_driverClassName != null && _connectionUrl != null)
        {
            Class.forName(_driverClassName);
        }
        else
        {
            try
            {
                InitialContext ic = new InitialContext();
                _datasource = (DataSource)ic.lookup("jdbc/sessions"); //last ditch effort
            }
            catch (NamingException e)
            {
                throw new IllegalStateException("No database configured for sessions");
            }
        }
    }
