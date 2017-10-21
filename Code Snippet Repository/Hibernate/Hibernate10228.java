		private Object[] computeParams() throws Exception {
			try {
				return fParameterList.get( fParameterSetNumber );
			}
			catch (ClassCastException e) {
				throw new Exception(
						String.format(
								"%s.%s() must return a Collection of arrays.",
								getTestClass().getName(), getParametersMethod(
								getTestClass()
						).getName()
						)
				);
			}
		}
