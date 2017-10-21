        String describe(Session session, int blanks) {

            StringBuffer sb = new StringBuffer();
            StringBuffer b  = new StringBuffer(blanks);

            for (int i = 0; i < blanks; i++) {
                b.append(' ');
            }

            sb.append("index=").append(rangeIndex.getName().name).append("\n");

            if (hasIndexCondition()) {
                if (indexedColumnCount > 0) {
                    sb.append(b).append("start conditions=[");

                    for (int j = 0; j < indexedColumnCount; j++) {
                        if (indexCond != null && indexCond[j] != null) {
                            sb.append(indexCond[j].describe(session, blanks));
                        }
                    }

                    sb.append("]\n");
                }

                if (this.opTypeEnd != OpTypes.EQUAL
                        && indexEndCondition != null) {
                    String temp = indexEndCondition.describe(session, blanks);

                    sb.append(b).append("end condition=[").append(temp).append(
                        "]\n");
                }
            }

            if (nonIndexCondition != null) {
                String temp = nonIndexCondition.describe(session, blanks);

                sb.append(b).append("other condition=[").append(temp).append(
                    "]\n");
            }

            return sb.toString();
        }
