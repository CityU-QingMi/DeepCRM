    @Test
    public void testDataSource() throws NamingException, SQLException {
        final Connection connection1 = mock(Connection.class);
        final Connection connection2 = mock(Connection.class);

        given(dataSource.getConnection()).willReturn(connection1, connection2);

        DataSourceConnectionSource source = DataSourceConnectionSource.createConnectionSource(jndiURL);

        assertNotNull("The connection source should not be null.", source);
        assertEquals("The toString value is not correct.", "dataSource{ name=" + jndiURL + ", value="
            + dataSource + " }", source.toString());
        assertSame("The connection is not correct (1).", connection1, source.getConnection());
        assertSame("The connection is not correct (2).", connection2, source.getConnection());

        source = DataSourceConnectionSource.createConnectionSource(jndiURL.substring(0, jndiURL.length() - 1));

        assertNull("The connection source should be null now.", source);
    }
