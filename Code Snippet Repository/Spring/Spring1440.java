	@Test
	public void resolveFromSystemProperties() {
		getModifiableSystemEnvironment().put("otherKey", "systemValue");
		p1BeanDef = rootBeanDefinition(TestBean.class)
				.addPropertyValue("name", "${" + P1 + "}")
				.addPropertyValue("sex", "${otherKey}")
				.getBeanDefinition();
		registerWithGeneratedName(p1BeanDef, bf);
		ppc.postProcessBeanFactory(bf);
		TestBean bean = bf.getBean(TestBean.class);
		assertThat(bean.getName(), equalTo(P1_LOCAL_PROPS_VAL));
		assertThat(bean.getSex(), equalTo("systemValue"));
		getModifiableSystemEnvironment().remove("otherKey");
	}
