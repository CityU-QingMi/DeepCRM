    static public void main(String[] sa) throws SQLException {
        if (sa.length != 1) throw new IllegalArgumentException(SYNTAX_MSG);
        String authSpringFile = null;
        if (sa[0].equals("LDAP")) {
            authSpringFile = "ldapbeans.xml";
        } else if (sa[0].equals("JAAS")) {
            authSpringFile = "jaasbeans.xml";
        } else if (sa[0].equals("HsqldbSlave")) {
            authSpringFile = "slavebeans.xml";
        } else if (sa[0].equals("JAAS_LDAP")) {
            authSpringFile = "jaasldapbeans.xml";
        }
        if (authSpringFile == null)
            throw new IllegalArgumentException(SYNTAX_MSG);

        SpringExtAuth.prepMemoryDatabases(!sa[0].equals("HsqldbSlave"));
        ApplicationContext ctx =
            new ClassPathXmlApplicationContext("beandefs.xml", authSpringFile);
        ListableBeanFactory bf = (ListableBeanFactory) ctx;
        JdbcAppClass appBean = bf.getBean("appBean", JdbcAppClass.class);
        appBean.doJdbcWork();
    }
