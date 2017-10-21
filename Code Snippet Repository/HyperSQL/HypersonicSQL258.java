        public String getSchemaQualifiedStatementName() {

            switch (type) {

                case SchemaObject.PARAMETER :
                case SchemaObject.VARIABLE : {
                    return statementName;
                }
                case SchemaObject.COLUMN : {
                    if (parent == null
                            || SqlInvariants.SYSTEM_SUBQUERY.equals(
                                parent.name)) {
                        return statementName;
                    }

                    StringBuffer sb = new StringBuffer();

                    if (schema != null) {
                        sb.append(schema.getStatementName());
                        sb.append('.');
                    }

                    sb.append(parent.getStatementName());
                    sb.append('.');
                    sb.append(statementName);

                    return sb.toString();
                }
            }

            if (schema == null
                    || SqlInvariants.SYSTEM_SCHEMA.equals(schema.name)) {
                return statementName;
            }

            StringBuffer sb = new StringBuffer();

            sb.append(schema.getStatementName());
            sb.append('.');
            sb.append(statementName);

            return sb.toString();
        }
