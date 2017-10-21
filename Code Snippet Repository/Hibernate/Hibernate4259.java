		@Override
		protected Output buildExtendedReturn() {
			ProcedureOutputsImpl.this.refCursorParamIndex++;
			final ParameterRegistrationImplementor refCursorParam = ProcedureOutputsImpl.this.refCursorParameters[refCursorParamIndex];
			ResultSet resultSet;
			if ( refCursorParam.getName() != null ) {
				resultSet = ProcedureOutputsImpl.this.procedureCall.getSession().getFactory().getServiceRegistry()
						.getService( RefCursorSupport.class )
						.getResultSet( ProcedureOutputsImpl.this.callableStatement, refCursorParam.getName() );
			}
			else {
				resultSet = ProcedureOutputsImpl.this.procedureCall.getSession().getFactory().getServiceRegistry()
						.getService( RefCursorSupport.class )
						.getResultSet( ProcedureOutputsImpl.this.callableStatement, refCursorParam.getPosition() );
			}
			return buildResultSetOutput( () -> extractResults( resultSet ) );
		}
