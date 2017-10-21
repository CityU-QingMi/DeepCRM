		private Object validate(Class expectedClass, Object visitee) {
			if ( !visitee.getClass().getName().equals( expectedClass.getName() ) ) {
				throw new IllegalStateException(
						visitee.getClass().getName()
								+ " did not call proper accept method. Was "
								+ expectedClass.getName()
				);
			}
			return null;
		}
