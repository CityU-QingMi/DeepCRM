    OrderedHashSet readColumnNames(BitMap quotedFlags, boolean readAscDesc) {

        readThis(Tokens.OPENBRACKET);

        OrderedHashSet set = new OrderedHashSet();

        readColumnNameList(set, quotedFlags, readAscDesc);
        readThis(Tokens.CLOSEBRACKET);

        return set;
    }
