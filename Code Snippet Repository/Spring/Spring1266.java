		@Override
		public Object postProcessAfterInitialization(Object bean, String beanName) {
			if (bean instanceof FactoryBean) {
				return bean;
			}
			AtomicInteger c = count.get(beanName);
			if (c == null) {
				c = new AtomicInteger(0);
				count.put(beanName, c);
			}
			c.incrementAndGet();
			return bean;
		}
