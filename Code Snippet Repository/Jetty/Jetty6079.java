    protected Param findParamForRole(Role role)
    {
        for (Param param : params)
        {
            if (param.role == role)
            {
                return param;
            }
        }
        return null;
    }
