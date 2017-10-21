		@Bean
		public POBPP beanPostProcessor() {
			return new POBPP() {

				String nameSuffix = "-processed-" + myProp;

				public void setNameSuffix(String nameSuffix) {
					this.nameSuffix = nameSuffix;
				}

				@Override
				public Object postProcessBeforeInitialization(Object bean, String beanName) {
					if (bean instanceof ITestBean) {
						((ITestBean) bean).setName(((ITestBean) bean).getName() + nameSuffix);
					}
					return bean;
				}

				@Override
				public Object postProcessAfterInitialization(Object bean, String beanName) {
					return bean;
				}

				public int getOrder() {
					return 0;
				}
			};
		}
