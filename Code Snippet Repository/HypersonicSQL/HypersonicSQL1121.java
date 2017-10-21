    final void addIndex(Session session, Index index) {

        Index[] list = getNewIndexArray(index, indexList);

        try {
            resetAccessorKeys(session, list);
        } catch (HsqlException e) {
            for (int i = 0; i < indexList.length; i++) {
                indexList[i].setPosition(i);
            }

            throw e;
        }

        indexList = list;

        setBestRowIdentifiers();
    }
