	@Test
	public void testReflectionOptimization() {
		BytecodeProvider provider = Environment.getBytecodeProvider();
		ReflectionOptimizer optimizer = provider.getReflectionOptimizer(
				Bean.class,
		        BeanReflectionHelper.getGetterNames(),
		        BeanReflectionHelper.getSetterNames(),
		        BeanReflectionHelper.getTypes()
		);
		assertNotNull( optimizer );
		assertNotNull( optimizer.getInstantiationOptimizer() );
		assertNotNull( optimizer.getAccessOptimizer() );

		Object instance = optimizer.getInstantiationOptimizer().newInstance();
		assertEquals( instance.getClass(), Bean.class );
		Bean bean = ( Bean ) instance;

		optimizer.getAccessOptimizer().setPropertyValues( bean, BeanReflectionHelper.TEST_VALUES );
		assertEquals( bean.getSomeString(), BeanReflectionHelper.TEST_VALUES[0] );
		Object[] values = optimizer.getAccessOptimizer().getPropertyValues( bean );
		assertEquivalent( values, BeanReflectionHelper.TEST_VALUES );
	}
