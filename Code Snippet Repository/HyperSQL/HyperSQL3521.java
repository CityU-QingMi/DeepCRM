    void setColumnAliases(SimpleName[] names) {

        if (names.length != indexLimitVisible) {
            throw Error.error(ErrorCode.X_42593);
        }

        for (int i = 0; i < indexLimitVisible; i++) {
            exprColumns[i].setAlias(names[i]);
        }
    }
