    public static int getDayOfWeek(String name) {

        int c = Character.toUpperCase(name.charAt(0));

        switch (c) {

            case 'M' :
                return 2;

            case 'T' :
                if (Character.toUpperCase(name.charAt(1)) == 'U') {
                    return 3;
                } else if (Character.toUpperCase(name.charAt(1)) == 'H') {
                    return 5;
                }
                break;

            case 'W' :
                return 4;

            case 'F' :
                return 6;

            case 'S' :
                if (Character.toUpperCase(name.charAt(1)) == 'A') {
                    return 7;
                } else if (Character.toUpperCase(name.charAt(1)) == 'U') {
                    return 1;
                }
                break;
        }

        throw Error.error(ErrorCode.X_22007);
    }
