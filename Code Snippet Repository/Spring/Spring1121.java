	@Test
	public void cornerSpr9453() throws IntrospectionException {
		final class Bean implements Spr9453<Class<?>> {
			@Override
			public Class<?> getProp() {
				return null;
			}
		}
		{ // always passes
			BeanInfo info = Introspector.getBeanInfo(Bean.class);
			assertThat(info.getPropertyDescriptors().length, equalTo(2));
		}
		{ // failed prior to fix for SPR-9453
			BeanInfo info = new ExtendedBeanInfo(Introspector.getBeanInfo(Bean.class));
			assertThat(info.getPropertyDescriptors().length, equalTo(2));
		}
	}
