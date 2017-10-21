    static int get(String token) {

        int type = reservedKeys.get(token, -1);

        if (type == -1) {
            return commandSet.get(token, -1);
        }

        return type;
    }
