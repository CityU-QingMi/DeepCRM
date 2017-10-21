	@Test
	public void testUsageWithConnectionPropertiesAndUserCredentials() throws Exception {
		final String jdbcUrl = "url";
		final String uname = "uname";
		final String pwd = "pwd";

		final Properties connProps = new Properties();
		connProps.setProperty("myProp", "myValue");
		connProps.setProperty("yourProp", "yourValue");
		connProps.setProperty("user", "uname2");
		connProps.setProperty("password", "pwd2");

		class TestDriverManagerDataSource extends DriverManagerDataSource {
			@Override
			protected Connection getConnectionFromDriverManager(String url, Properties props) {
				assertEquals(jdbcUrl, url);
				assertEquals(uname, props.getProperty("user"));
				assertEquals(pwd, props.getProperty("password"));
				assertEquals("myValue", props.getProperty("myProp"));
				assertEquals("yourValue", props.getProperty("yourProp"));
				return connection;
			}
		}

		DriverManagerDataSource ds = new TestDriverManagerDataSource();
		//ds.setDriverClassName("foobar");
		ds.setUrl(jdbcUrl);
		ds.setUsername(uname);
		ds.setPassword(pwd);
		ds.setConnectionProperties(connProps);

		Connection actualCon = ds.getConnection();
		assertTrue(actualCon == connection);

		assertTrue(ds.getUrl().equals(jdbcUrl));
		assertTrue(ds.getPassword().equals(pwd));
		assertTrue(ds.getUsername().equals(uname));
	}
