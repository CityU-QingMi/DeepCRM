    public void setJoinType(boolean isLeft, boolean isRight) {

        isJoin      = true;
        isLeftJoin  = isLeft;
        isRightJoin = isRight;

        if (isRightJoin) {
            whereConditions[0].rangeIndex = rangeTable.getPrimaryIndex();
        }
    }
