	protected void registerBeans() {
		// The beans property may be null, for example if we are relying solely on autodetection.
		if (this.beans == null) {
			this.beans = new HashMap<>();
			// Use AUTODETECT_ALL as default in no beans specified explicitly.
			if (this.autodetectMode == null) {
				this.autodetectMode = AUTODETECT_ALL;
			}
		}

		// Perform autodetection, if desired.
		int mode = (this.autodetectMode != null ? this.autodetectMode : AUTODETECT_NONE);
		if (mode != AUTODETECT_NONE) {
			if (this.beanFactory == null) {
				throw new MBeanExportException("Cannot autodetect MBeans if not running in a BeanFactory");
			}
			if (mode == AUTODETECT_MBEAN || mode == AUTODETECT_ALL) {
				// Autodetect any beans that are already MBeans.
				logger.debug("Autodetecting user-defined JMX MBeans");
				autodetect(this.beans, (beanClass, beanName) -> isMBean(beanClass));
			}
			// Allow the assembler a chance to vote for bean inclusion.
			if ((mode == AUTODETECT_ASSEMBLER || mode == AUTODETECT_ALL) &&
					this.assembler instanceof AutodetectCapableMBeanInfoAssembler) {
				autodetect(this.beans, ((AutodetectCapableMBeanInfoAssembler) this.assembler)::includeBean);
			}
		}

		if (!this.beans.isEmpty()) {
			this.beans.forEach((beanName, instance) -> registerBeanNameOrInstance(instance, beanName));
		}
	}
