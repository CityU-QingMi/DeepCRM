    private void setCursors() {

        if (cursors.length == 0) {
            return;
        }

        HashSet list = new HashSet();

        for (int i = 0; i < cursors.length; i++) {
            StatementCursor cursor = cursors[i];
            boolean         added  = list.add(cursor.getCursorName().name);

            if (!added) {
                throw Error.error(ErrorCode.X_42606,
                                  cursor.getCursorName().name);
            }
        }
    }
