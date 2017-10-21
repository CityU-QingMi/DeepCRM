	private List<TestExecutionListener> instantiateListeners(List<Class<? extends TestExecutionListener>> classesList) {
		List<TestExecutionListener> listeners = new ArrayList<>(classesList.size());
		for (Class<? extends TestExecutionListener> listenerClass : classesList) {
			NoClassDefFoundError ncdfe = null;
			try {
				listeners.add(BeanUtils.instantiateClass(listenerClass));
			}
			catch (NoClassDefFoundError err) {
				ncdfe = err;
			}
			catch (BeanInstantiationException ex) {
				if (ex.getCause() instanceof NoClassDefFoundError) {
					ncdfe = (NoClassDefFoundError) ex.getCause();
				}
			}
			if (ncdfe != null) {
				if (logger.isInfoEnabled()) {
					logger.info(String.format("Could not instantiate TestExecutionListener [%s]. " +
							"Specify custom listener classes or make the default listener classes " +
							"(and their required dependencies) available. Offending class: [%s]",
							listenerClass.getName(), ncdfe.getMessage()));
				}
			}
		}
		return listeners;
	}
