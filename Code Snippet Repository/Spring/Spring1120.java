	@Test
	public void cornerSpr9414() throws IntrospectionException {
		@SuppressWarnings("unused") class Parent {
			public Number getProperty1() {
				return 1;
			}
		}
		class Child extends Parent {
			@Override
			public Integer getProperty1() {
				return 2;
			}
		}
		{ // always passes
			ExtendedBeanInfo bi = new ExtendedBeanInfo(Introspector.getBeanInfo(Parent.class));
			assertThat(hasReadMethodForProperty(bi, "property1"), is(true));
		}
		{ // failed prior to fix for SPR-9414
			ExtendedBeanInfo bi = new ExtendedBeanInfo(Introspector.getBeanInfo(Child.class));
			assertThat(hasReadMethodForProperty(bi, "property1"), is(true));
		}
	}
