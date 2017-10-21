    public String getFullNameString() {

        switch (typeCode) {

            case Types.SQL_DOUBLE :
                return "DOUBLE PRECISION";

            default :
                return getNameString();
        }
    }
