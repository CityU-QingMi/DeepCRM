    public Routine getSpecificRoutine(Type[] types) {

        Routine routine = findSpecificRoutine(types);

        if (routine == null) {
            StringBuffer sb = new StringBuffer();

            sb.append(name.getSchemaQualifiedStatementName());
            sb.append(Tokens.T_OPENBRACKET);

            for (int i = 0; i < types.length; i++) {
                if (i != 0) {
                    sb.append(Tokens.T_COMMA);
                }

                sb.append(types[i].getNameString());
            }

            sb.append(Tokens.T_CLOSEBRACKET);

            throw Error.error(ErrorCode.X_42609, sb.toString());
        }

        return routine;
    }
