		@Override
		public E createElement(int index) {
			try {
				return ReflectionUtils.accessibleConstructor(this.elementClass).newInstance();
			}
			catch (NoSuchMethodException ex) {
				throw new ElementInstantiationException(
						"No default constructor on element class: " + this.elementClass.getName(), ex);
			}
			catch (InstantiationException ex) {
				throw new ElementInstantiationException(
						"Unable to instantiate element class: " + this.elementClass.getName(), ex);
			}
			catch (IllegalAccessException ex) {
				throw new ElementInstantiationException(
						"Could not access element constructor: " + this.elementClass.getName(), ex);
			}
			catch (InvocationTargetException ex) {
				throw new ElementInstantiationException(
						"Failed to invoke element constructor: " + this.elementClass.getName(), ex.getTargetException());
			}
		}
