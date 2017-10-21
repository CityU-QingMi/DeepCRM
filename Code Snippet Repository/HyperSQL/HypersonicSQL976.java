    private Statement recompileStatement(Session session, Statement cs) {

        HsqlName  oldSchema = session.getCurrentSchemaHsqlName();
        Statement newStatement;

        // revalidate with the original schema
        try {
            HsqlName schema = cs.getSchemaName();
            int      props  = cs.getCursorPropertiesRequest();

            if (schema != null) {

                // checks the old schema exists
                session.setSchema(schema.name);
            }

            boolean setGenerated = cs.generatedResultMetaData() != null;

            newStatement = session.compileStatement(cs.getSQL(), props);

            newStatement.setCursorPropertiesRequest(props);

            if (!cs.getResultMetaData().areTypesCompatible(
                    newStatement.getResultMetaData())) {
                return null;
            }

            if (!cs.getParametersMetaData().areTypesCompatible(
                    newStatement.getParametersMetaData())) {
                return null;
            }

            newStatement.setCompileTimestamp(
                database.txManager.getGlobalChangeTimestamp());

            if (setGenerated) {
                StatementDML si = (StatementDML) cs;

                newStatement.setGeneratedColumnInfo(si.generatedType,
                                                    si.generatedInputMetaData);
            }
        } catch (Throwable t) {
            return null;
        } finally {
            session.setCurrentSchemaHsqlName(oldSchema);
        }

        return newStatement;
    }
