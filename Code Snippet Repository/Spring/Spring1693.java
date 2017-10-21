		@Override
		public PropertyDescriptor[] getPropertyDescriptors() {
			try {
				PropertyDescriptor pd = new PropertyDescriptor("value", ValueBean.class);
				pd.setPropertyEditorClass(MyNumberEditor.class);
				return new PropertyDescriptor[] {pd};
			}
			catch (IntrospectionException ex) {
				throw new FatalBeanException("Couldn't create PropertyDescriptor", ex);
			}
		}
