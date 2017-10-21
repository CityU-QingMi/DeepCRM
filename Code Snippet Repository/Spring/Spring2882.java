		@Override
		@Nullable
		protected Object[] getAdvicesAndAdvisorsForBean(Class<?> beanClass, String name, @Nullable TargetSource customTargetSource) {
			if (StaticMessageSource.class.equals(beanClass)) {
				return DO_NOT_PROXY;
			}
			else if (name.endsWith("ToBeProxied")) {
				boolean isFactoryBean = FactoryBean.class.isAssignableFrom(beanClass);
				if ((this.proxyFactoryBean && isFactoryBean) || (this.proxyObject && !isFactoryBean)) {
					return new Object[] {this.testInterceptor};
				}
				else {
					return DO_NOT_PROXY;
				}
			}
			else {
				return PROXY_WITHOUT_ADDITIONAL_INTERCEPTORS;
			}
		}
