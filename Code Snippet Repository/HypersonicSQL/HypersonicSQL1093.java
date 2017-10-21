    IndexUse[] findIndexForColumns(Session session, OrderedIntHashSet set,
                                   int opType, boolean ordered) {

        IndexUse[] indexUse = Index.emptyUseArray;

        if (set.isEmpty()) {
            return Index.emptyUseArray;
        }

        for (int i = 0, count = indexList.length; i < count; i++) {
            Index currentIndex = getIndex(i);
            int[] indexcols    = currentIndex.getColumns();
            int matchCount = ordered ? set.getOrderedStartMatchCount(indexcols)
                                     : set.getStartMatchCount(indexcols);

            if (matchCount == 0) {
                continue;
            }

            if (matchCount == set.size()) {
                return currentIndex.asArray();
            }

            if (matchCount == currentIndex.getColumnCount()) {
                if (currentIndex.isUnique()) {
                    return currentIndex.asArray();
                }
            }

            if (indexUse.length == 0
                    && matchCount == currentIndex.getColumnCount()) {
                indexUse = currentIndex.asArray();
            } else {
                IndexUse[] newList = new IndexUse[indexUse.length + 1];

                ArrayUtil.copyArray(indexUse, newList, indexUse.length);

                newList[newList.length - 1] = new IndexUse(currentIndex,
                        matchCount);
                indexUse = newList;
            }
        }

        return indexUse;
    }
