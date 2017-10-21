    protected void processConstraintMappingWithMethodOmissions (ConstraintMapping mapping, Map<String, RoleInfo> mappings)
    {
        String[] omissions = mapping.getMethodOmissions();
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<omissions.length; i++)
        {
            if (i > 0)
                sb.append(".");
            sb.append(omissions[i]);
        }
        sb.append(OMISSION_SUFFIX);
        RoleInfo ri = new RoleInfo();
        mappings.put(sb.toString(), ri);
        configureRoleInfo(ri, mapping);
    }
