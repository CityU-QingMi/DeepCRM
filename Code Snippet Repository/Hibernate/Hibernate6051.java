	private void createProcedures(EntityManagerFactory emf) {
		createProcedure(
				emf,
				"CREATE OR REPLACE PROCEDURE PROC_EXAMPLE_ONE_BASIC_OUT ( " +
						"  ID_PARAM IN NUMBER, NAME_PARAM OUT VARCHAR2 ) " +
						"AS " +
						"BEGIN " +
						"  SELECT NAME INTO NAME_PARAM FROM USERS WHERE id = ID_PARAM; " +
						"END PROC_EXAMPLE_ONE_BASIC_OUT; "
		);

		createProcedure(
				emf,
				"CREATE OR REPLACE PROCEDURE PROC_EXAMPLE_TWO_BASIC_OUT ( " +
						"  ID_PARAM IN NUMBER, NAME_PARAM OUT VARCHAR2, AGE_PARAM OUT NUMBER ) " +
						"AS " +
						"BEGIN " +
						"  SELECT NAME, AGE INTO NAME_PARAM, AGE_PARAM FROM USERS WHERE id = ID_PARAM; " +
						"END PROC_EXAMPLE_TWO_BASIC_OUT; "
		);
	}
