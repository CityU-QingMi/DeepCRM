    @Test
    public void testDataSourceReturnType() throws SQLException {
        final DataSource dataSource = mock(DataSource.class);
        final Connection connection1 = mock(Connection.class);
        final Connection connection2 = mock(Connection.class);

        given(dataSource.getConnection()).willReturn(connection1, connection2);

        holder.set(dataSource);

        final FactoryMethodConnectionSource source = FactoryMethodConnectionSource.createConnectionSource(
                DataSourceFactory.class.getName(), "factoryMethod02"
        );

        assertNotNull("The connection source should not be null.", source);
        assertEquals("The toString value is not correct.", "factory{ public static javax.sql.DataSource[" + dataSource
                + "] " + DataSourceFactory.class.getName() + ".factoryMethod02() }", source.toString());
        assertSame("The connection is not correct (1).", connection1, source.getConnection());
        assertSame("The connection is not correct (2).", connection2, source.getConnection());
    }
