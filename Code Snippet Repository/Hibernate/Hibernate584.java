	public static SchemaAutoTooling interpret(String configurationValue) {
		if ( StringHelper.isEmpty( configurationValue ) || NONE.externalForm.equals( configurationValue ) ) {
			return null;
		}
		else if ( VALIDATE.externalForm.equals( configurationValue ) ) {
			return VALIDATE;
		}
		else if ( UPDATE.externalForm.equals( configurationValue ) ) {
			return UPDATE;
		}
		else if ( CREATE.externalForm.equals( configurationValue ) ) {
			return CREATE;
		}
		else if ( CREATE_DROP.externalForm.equals( configurationValue ) ) {
			return CREATE_DROP;
		}
		else {
			throw new HibernateException(
					"Unrecognized hbm2ddl_auto value : " + configurationValue
							+ ".  Supported values include 'create', 'create-drop', 'update', 'none' and 'validate'."
			);
		}
	}
