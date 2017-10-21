    private boolean compareAt(Session session, Object o, int i, int j,
                              int iLen, int jLen, char[] cLike,
                              int[] wildCardType) {

        for (; i < iLen; i++) {
            switch (wildCardType[i]) {

                case 0 :                  // general character
                    if ((j >= jLen)
                            || (cLike[i] != getChar(session, o, j++))) {
                        return false;
                    }
                    break;

                case UNDERSCORE_CHAR :    // underscore: do not test this character
                    if (j++ >= jLen) {
                        return false;
                    }
                    break;

                case PERCENT_CHAR :       // percent: none or any character(s)
                    if (++i >= iLen) {
                        return true;
                    }

                    while (j < jLen) {
                        if ((cLike[i] == getChar(session, o, j))
                                && compareAt(session, o, i, j, iLen, jLen,
                                             cLike, wildCardType)) {
                            return true;
                        }

                        j++;
                    }

                    return false;
            }
        }

        if (j != jLen) {
            return false;
        }

        return true;
    }
