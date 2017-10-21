    public String getSQL() {

        StringBuffer sb = new StringBuffer();

        if (schemaObjectType == SchemaObject.TYPE) {
            sb.append(Tokens.T_CREATE).append(' ').append(
                Tokens.T_TYPE).append(' ');
            sb.append(name.getSchemaQualifiedStatementName());
            sb.append(' ').append(Tokens.T_AS).append(' ');
            sb.append(dataType.getDefinition());

            if (dataType.isCharacterType() ) {
                Collation collation = dataType.getCollation();
                if (collation.isObjectCollation()) {
                    sb.append(' ').append(collation.getCollateSQL());
                }
            }
        } else {
            sb.append(Tokens.T_CREATE).append(' ').append(
                Tokens.T_DOMAIN).append(' ');
            sb.append(name.getSchemaQualifiedStatementName());
            sb.append(' ').append(Tokens.T_AS).append(' ');
            sb.append(dataType.getDefinition());

            if (defaultExpression != null) {
                sb.append(' ').append(Tokens.T_DEFAULT).append(' ');
                sb.append(defaultExpression.getSQL());
            }

            for (int i = 0; i < constraints.length; i++) {
                sb.append(' ').append(Tokens.T_CONSTRAINT).append(' ');
                sb.append(constraints[i].getName().statementName).append(' ');
                sb.append(Tokens.T_CHECK).append('(').append(
                    constraints[i].getCheckSQL()).append(')');
            }
        }

        return sb.toString();
    }
