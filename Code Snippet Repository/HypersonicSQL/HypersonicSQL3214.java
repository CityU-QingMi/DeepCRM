    public static int getRandomID(int type) {

        int min     = 0,
            max     = naccounts * tps - 1;
        int account = getRandomInt(min, max);

        switch (type) {

            case TELLER :
                max = ntellers * tps - 1;

                return getRandomInt(min, max);
        }

        return account;
    }
