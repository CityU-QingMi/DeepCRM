    public boolean canAccessFully(int privilegeType) {

        if (isFull) {
            return true;
        }

        switch (privilegeType) {

            case GrantConstants.DELETE :
                return isFullDelete;

            case GrantConstants.SELECT :
                return isFullSelect;

            case GrantConstants.INSERT :
                return isFullInsert;

            case GrantConstants.UPDATE :
                return isFullUpdate;

            case GrantConstants.REFERENCES :
                return isFullReferences;

            case GrantConstants.TRIGGER :
                return isFullTrigger;

            case GrantConstants.EXECUTE :
                return isFull;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "Right");
        }
    }
