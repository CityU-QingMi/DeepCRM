    @Test
    public void testDataSourceConfig() throws Exception {
        try (final Connection connection = jdbcRule.getConnectionSource().getConnection()) {
            final Error exception = new Error("Final error massage is fatal!");
            final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            final PrintWriter writer = new PrintWriter(outputStream);
            exception.printStackTrace(writer);
            writer.close();
            final String stackTrace = outputStream.toString();

            final long millis = System.currentTimeMillis();

            final Logger logger = LogManager.getLogger(this.getClass().getName() + ".testDataSourceConfig");
            logger.trace("Data source logged message 01.");
            logger.fatal("Error from data source 02.", exception);

            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM dsLogEntry ORDER BY id");

            assertTrue("There should be at least one row.", resultSet.next());

            final long date = resultSet.getTimestamp("eventDate").getTime();
            assertTrue("The date should be later than pre-logging (1).", date >= millis);
            assertTrue("The date should be earlier than now (1).", date <= System.currentTimeMillis());
            assertEquals("The literal column is not correct (1).", "Literal Value of Data Source",
                resultSet.getString("literalColumn"));
            assertEquals("The level column is not correct (1).", "FATAL", resultSet.getNString("level"));
            assertEquals("The logger column is not correct (1).", logger.getName(), resultSet.getNString("logger"));
            assertEquals("The message column is not correct (1).", "Error from data source 02.",
                resultSet.getString("message"));
            assertEquals("The exception column is not correct (1).", stackTrace,
                IOUtils.readStringAndClose(resultSet.getNClob("exception").getCharacterStream(), -1));

            assertFalse("There should not be two rows.", resultSet.next());
        }
    }
