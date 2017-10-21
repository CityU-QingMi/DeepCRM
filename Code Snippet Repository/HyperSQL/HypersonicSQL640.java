    int getJoinedRangePosition(Expression e, int position,
                               RangeVariable[] currentRanges) {

        int found = -1;

        tempSet.clear();
        e.getJoinRangeVariables(currentRanges, tempSet);

        for (int i = 0; i < tempSet.size(); i++) {
            for (int j = 0; j < currentRanges.length; j++) {
                if (tempSet.get(i) == currentRanges[j]) {
                    if (j >= position) {
                        if (found > 0) {
                            return -1;
                        } else {
                            found = j;
                        }
                    }
                }
            }
        }

        return found;
    }
