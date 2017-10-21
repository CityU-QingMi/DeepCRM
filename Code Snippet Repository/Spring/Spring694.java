	public void setParent(Object obj) {
		if (obj == null) {
			throw new IllegalArgumentException("Parent bean cannot be set to a null runtime bean reference!");
		}
		if (obj instanceof String) {
			this.parentName = (String) obj;
		}
		else if (obj instanceof RuntimeBeanReference) {
			this.parentName = ((RuntimeBeanReference) obj).getBeanName();
		}
		else if (obj instanceof GroovyBeanDefinitionWrapper) {
			this.parentName = ((GroovyBeanDefinitionWrapper) obj).getBeanName();
		}
		getBeanDefinition().setParentName(this.parentName);
		getBeanDefinition().setAbstract(false);
	}
