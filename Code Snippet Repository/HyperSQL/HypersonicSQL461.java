    SimpleName[] readColumnNameList(OrderedHashSet set) {

        BitMap columnNameQuoted = new BitMap(0, true);

        readThis(Tokens.OPENBRACKET);
        readColumnNameList(set, columnNameQuoted, false);
        readThis(Tokens.CLOSEBRACKET);

        SimpleName[] columnNameList = new SimpleName[set.size()];

        for (int i = 0; i < set.size(); i++) {
            SimpleName name =
                HsqlNameManager.getSimpleName((String) set.get(i),
                                              columnNameQuoted.isSet(i));

            columnNameList[i] = name;
        }

        return columnNameList;
    }
