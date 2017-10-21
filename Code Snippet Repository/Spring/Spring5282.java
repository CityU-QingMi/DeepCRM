	@Test
	public void getSystemEnvironment_withAndWithoutSecurityManager() {
		getModifiableSystemEnvironment().put(ALLOWED_PROPERTY_NAME, ALLOWED_PROPERTY_VALUE);
		getModifiableSystemEnvironment().put(DISALLOWED_PROPERTY_NAME, DISALLOWED_PROPERTY_VALUE);

		{
			Map<String, Object> systemEnvironment = environment.getSystemEnvironment();
			assertThat(systemEnvironment, notNullValue());
			assertSame(systemEnvironment, System.getenv());
		}

		SecurityManager oldSecurityManager = System.getSecurityManager();
		SecurityManager securityManager = new SecurityManager() {
			@Override
			public void checkPermission(Permission perm) {
				//see http://download.oracle.com/javase/1.5.0/docs/api/java/lang/System.html#getenv()
				if ("getenv.*".equals(perm.getName())) {
					throw new AccessControlException("Accessing the system environment is disallowed");
				}
				//see http://download.oracle.com/javase/1.5.0/docs/api/java/lang/System.html#getenv(java.lang.String)
				if (("getenv."+DISALLOWED_PROPERTY_NAME).equals(perm.getName())) {
					throw new AccessControlException(
							String.format("Accessing the system environment variable [%s] is disallowed", DISALLOWED_PROPERTY_NAME));
				}
			}
		};
		System.setSecurityManager(securityManager);

		{
			Map<String, Object> systemEnvironment = environment.getSystemEnvironment();
			assertThat(systemEnvironment, notNullValue());
			assertThat(systemEnvironment, instanceOf(ReadOnlySystemAttributesMap.class));
			assertThat(systemEnvironment.get(ALLOWED_PROPERTY_NAME), equalTo((Object)ALLOWED_PROPERTY_VALUE));
			assertThat(systemEnvironment.get(DISALLOWED_PROPERTY_NAME), nullValue());
		}

		System.setSecurityManager(oldSecurityManager);
		getModifiableSystemEnvironment().remove(ALLOWED_PROPERTY_NAME);
		getModifiableSystemEnvironment().remove(DISALLOWED_PROPERTY_NAME);
	}
