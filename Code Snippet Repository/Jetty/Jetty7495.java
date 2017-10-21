     public static DataSourceLoginService configureLoginService () throws Exception
     {
         DataSourceLoginService loginService = new DataSourceLoginService();
         loginService.setUserTableName("users");
         loginService.setUserTableKey("id");
         loginService.setUserTableUserField("username");
         loginService.setUserTablePasswordField("pwd");
         loginService.setRoleTableName("roles");
         loginService.setRoleTableKey("id");
         loginService.setRoleTableRoleField("role");
         loginService.setUserRoleTableName("user_roles");
         loginService.setUserRoleTableRoleKey("role_id");
         loginService.setUserRoleTableUserKey("user_id");
         loginService.setJndiName("dstest");
         loginService.setName(__realm);
         if (_testServer != null)
             loginService.setServer(_testServer.getServer());
         
         //create a datasource
         EmbeddedDataSource ds = new EmbeddedDataSource();
         File db = new File (DatabaseLoginServiceTestServer.getDbRoot(), "loginservice");
         ds.setDatabaseName(db.getAbsolutePath());
         org.eclipse.jetty.plus.jndi.Resource binding = new org.eclipse.jetty.plus.jndi.Resource(null, "dstest",
                                                                                                      ds);
         assertThat("Created binding for dstest", binding, notNullValue());
         return loginService;
     }
