    public String getSQL() {

        StringBuffer sb = new StringBuffer();

        sb.append(routineSchema.getName().getSchemaQualifiedStatementName());
        sb.append('(');

        int nodeCount = nodes.length;

        if (opType == OpTypes.USER_AGGREGATE) {
            nodeCount = 1;
        }

        for (int i = 0; i < nodeCount; i++) {
            if (i != 0) {
                sb.append(',');
            }

            sb.append(nodes[i].getSQL());
        }

        sb.append(')');

        return sb.toString();
    }
