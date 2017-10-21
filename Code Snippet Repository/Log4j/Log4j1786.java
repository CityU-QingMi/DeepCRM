    @Test
    public void testConnectionReturnType() throws SQLException {
        final Connection connection = mock(Connection.class);

        holder.set(connection);

        final FactoryMethodConnectionSource source = FactoryMethodConnectionSource.createConnectionSource(
                ConnectionFactory.class.getName(), "anotherMethod03"
        );

        assertNotNull("The connection source should not be null.", source);
        assertEquals("The toString value is not correct.", "factory{ public static java.sql.Connection "
                + ConnectionFactory.class.getName() + ".anotherMethod03() }", source.toString());
        assertSame("The connection is not correct (1).", connection, source.getConnection());
        assertSame("The connection is not correct (2).", connection, source.getConnection());
    }
