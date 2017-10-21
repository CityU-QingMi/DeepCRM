    public static int getRandomID(int type) {

        int min = 0,
            max = 0;

        switch (type) {

            case TELLER :
                max = ntellers * tps - 1;
                break;

            case BRANCH :
                max = nbranches * tps - 1;
                break;

            case ACCOUNT :
                max = naccounts * tps - 1;
                break;
        }

        return getRandomInt(min, max);
    }
