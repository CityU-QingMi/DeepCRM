    void moveConditions(HsqlList[] lists, int rangeStart, int rangeLimit,
                        HsqlList list, int listIndex) {

        for (int j = 0; j < list.size(); j++) {
            Expression e = (Expression) list.get(j);

            tempSet.clear();
            e.collectRangeVariables(rangeVariables, tempSet);

            int index = rangeVarSet.getSmallestIndex(tempSet);

            if (index < rangeStart) {
                continue;
            }

            index = rangeVarSet.getLargestIndex(tempSet);

            if (index >= rangeLimit) {
                continue;
            }

            if (index != listIndex) {
                list.remove(j);
                lists[index].add(e);

                j--;
            }
        }
    }
