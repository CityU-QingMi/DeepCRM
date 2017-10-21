    boolean isEquivalentToNotNullPredicate() {

        if (isVariable || isNull || iFirstWildCard == -1) {
            return false;
        }

        for (int i = 0; i < wildCardType.length; i++) {
            if (wildCardType[i] != PERCENT_CHAR) {
                return false;
            }
        }

        return true;
    }
