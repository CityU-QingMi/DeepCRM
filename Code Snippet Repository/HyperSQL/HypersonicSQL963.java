    private StringBuffer appendSubqueries(Session session, StringBuffer sb,
                                          int blanks) {

        sb.append("SUBQUERIES[");

        for (int i = 0; i < subqueries.length; i++) {
            sb.append("\n[level=").append(subqueries[i].depth).append('\n');

            if (subqueries[i].queryExpression == null) {
                for (int j = 0; j < blanks; j++) {
                    sb.append(' ');
                }

                sb.append("value expression");
            } else {
                sb.append(subqueries[i].queryExpression.describe(session,
                        blanks));
            }

            sb.append("]");
        }

        sb.append(']');

        return sb;
    }
