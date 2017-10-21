    public String describe(Session session, int blanks) {

        RangeVariableConditions[] conditionsArray = joinConditions;
        StringBuffer              sb;
        String b = ValuePool.spaceString.substring(0, blanks);

        sb = new StringBuffer();

        String temp = "INNER";

        if (isLeftJoin) {
            temp = "LEFT OUTER";

            if (isRightJoin) {
                temp = "FULL";
            }
        } else if (isRightJoin) {
            temp = "RIGHT OUTER";
        }

        sb.append(b).append("join type=").append(temp).append("\n");
        sb.append(b).append("table=").append(rangeTable.getName().name).append(
            "\n");

        if (tableAlias != null) {
            sb.append(b).append("alias=").append(tableAlias.name).append("\n");
        }

        boolean fullScan = !conditionsArray[0].hasIndexCondition();

        sb.append(b).append("access=").append(fullScan ? "FULL SCAN"
                                                       : "INDEX PRED").append(
                                                       "\n");

        for (int i = 0; i < conditionsArray.length; i++) {
            RangeVariableConditions conditions = this.joinConditions[i];

            if (i > 0) {
                sb.append(b).append("OR condition = [");
            } else {
                sb.append(b).append("condition = [");
            }

            sb.append(conditions.describe(session, blanks + 2));
            sb.append(b).append("]\n");
        }

        return sb.toString();
    }
