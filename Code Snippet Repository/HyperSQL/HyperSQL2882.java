    public Type getCombinedType(Session session, Type other, int operation) {

        switch (operation) {

            case OpTypes.EQUAL :
                if (other.isBooleanType()) {
                    return this;
                }
        }

        throw Error.error(ErrorCode.X_42562);
    }
