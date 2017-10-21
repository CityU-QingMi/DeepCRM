    public void call(Object endpoint, Throwable cause)
    {
        if (idxThrowable == (-1))
        {
            idxThrowable = findIndexForRole(Param.Role.ERROR_CAUSE);
            assertRoleRequired(idxThrowable,"Throwable");
        }

        if (idxThrowable >= 0)
        {
            super.args[idxThrowable] = cause;
        }
        super.call(endpoint,super.args);
    }
