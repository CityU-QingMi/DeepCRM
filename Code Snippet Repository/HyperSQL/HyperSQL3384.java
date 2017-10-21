    HsqlName[] readColumnNames(HsqlName tableName) {

        BitMap         quotedFlags = new BitMap(0, true);
        OrderedHashSet set         = readColumnNames(quotedFlags, false);
        HsqlName[]     colList     = new HsqlName[set.size()];

        for (int i = 0; i < colList.length; i++) {
            String  name   = (String) set.get(i);
            boolean quoted = quotedFlags.isSet(i);

            colList[i] = database.nameManager.newHsqlName(tableName.schema,
                    name, quoted, SchemaObject.COLUMN, tableName);
        }

        return colList;
    }
