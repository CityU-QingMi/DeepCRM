    public Index getNewIndex(Session session, TableBase table) {

        if (hasOrder()) {
            Index orderIndex = table.createAndAddIndexStructure(session, null,
                sortOrder, sortDescending, sortNullsLast, false, false, false);

            if (collations != null) {
                for (int i = 0; i < columnCount; i++) {
                    if (collations[i] != null) {
                        Type type = orderIndex.getColumnTypes()[i];

                        type = Type.getType(type.typeCode,
                                            type.getCharacterSet(),
                                            collations[i], type.precision,
                                            type.scale);
                        orderIndex.getColumnTypes()[i] = type;
                    }
                }
            }

            return orderIndex;
        }

        return null;
    }
