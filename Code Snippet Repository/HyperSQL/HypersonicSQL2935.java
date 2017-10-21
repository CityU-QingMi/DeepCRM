    String[] getTableRightsArray() {

        if (isFull) {
            return new String[] {
                Tokens.T_SELECT, Tokens.T_INSERT, Tokens.T_UPDATE,
                Tokens.T_DELETE, Tokens.T_REFERENCES
            };
        }

        HsqlArrayList list  = new HsqlArrayList();
        String[]      array = new String[list.size()];

        if (isFullSelect) {
            list.add(Tokens.T_SELECT);
        }

        if (isFullInsert) {
            list.add(Tokens.T_INSERT);
        }

        if (isFullUpdate) {
            list.add(Tokens.T_UPDATE);
        }

        if (isFullDelete) {
            list.add(Tokens.T_DELETE);
        }

        if (isFullReferences) {
            list.add(Tokens.T_REFERENCES);
        }

        if (isFullTrigger) {
            list.add(Tokens.T_TRIGGER);
        }

        list.toArray(array);

        return array;
    }
