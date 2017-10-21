    public void dropRole(String name) {

        if (!isRole(name)) {
            throw Error.error(ErrorCode.X_0P000, name);
        }

        if (GranteeManager.isReserved(name)) {
            throw Error.error(ErrorCode.X_42507);
        }

        removeGrantee(name);
    }
