	@Test
	public void testBridgeMethodDisregarded() {
		PropertyAccessStrategyBasicImpl accessStrategy = PropertyAccessStrategyBasicImpl.INSTANCE;

		{
			final PropertyAccess access = accessStrategy.buildPropertyAccess( Duper.class, "it" );
			assertEquals( String.class, access.getGetter().getReturnType() );
			assertEquals( Object.class, access.getSetter().getMethod().getParameterTypes()[0] );
		}

		{
			final PropertyAccess access = accessStrategy.buildPropertyAccess( Duper2.class, "it" );
			assertEquals( String.class, access.getGetter().getReturnType() );
			assertEquals( String.class, access.getSetter().getMethod().getParameterTypes()[0] );
		}
	}
