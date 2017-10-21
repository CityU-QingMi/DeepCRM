		@Override
		protected Object getTestInstance() throws Exception {
			Object testInstance = super.getTestInstance();
			if ( AbstractEnversTest.class.isInstance( testInstance ) ) {
				((AbstractEnversTest) testInstance).setTestData( computeParams() );
			}
			else if ( BaseEnversFunctionalTestCase.class.isInstance( testInstance ) ) {
				((BaseEnversFunctionalTestCase) testInstance).setTestData( computeParams() );
			}
			return testInstance;
		}
