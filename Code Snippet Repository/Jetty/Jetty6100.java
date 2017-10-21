    private int findMessageObjectIndex()
    {
        int index = -1;

        for (Param.Role role : Param.Role.getMessageRoles())
        {
            index = findIndexForRole(role);
            if (index >= 0)
            {
                return index;
            }
        }

        return -1;
    }
