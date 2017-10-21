	public Map<String, ProcedureCallMemento> buildProcedureCallMementos(SessionFactoryImpl sessionFactory) {
		final Map<String, ProcedureCallMemento> rtn = new HashMap<>();
		if ( namedProcedureCallMap != null ) {
			for ( NamedProcedureCallDefinition procedureCallDefinition : namedProcedureCallMap.values() ) {
				rtn.put(
						procedureCallDefinition.getRegisteredName(),
						procedureCallDefinition.toMemento( sessionFactory,sqlResultSetMappingMap )
				);
			}
		}
		return rtn;
	}
