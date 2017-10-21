    protected void processConstraintMapping(ConstraintMapping mapping)
    {
        Map<String, RoleInfo> mappings = _constraintMap.get(mapping.getPathSpec());
        if (mappings == null)
        {
            mappings = new HashMap<String,RoleInfo>();
            _constraintMap.put(mapping.getPathSpec(),mappings);
        }
        RoleInfo allMethodsRoleInfo = mappings.get(ALL_METHODS);
        if (allMethodsRoleInfo != null && allMethodsRoleInfo.isForbidden())
            return;

        if (mapping.getMethodOmissions() != null && mapping.getMethodOmissions().length > 0)
        {
            processConstraintMappingWithMethodOmissions(mapping, mappings);
            return;
        }

        String httpMethod = mapping.getMethod();
        if (httpMethod==null)
            httpMethod=ALL_METHODS;
        RoleInfo roleInfo = mappings.get(httpMethod);
        if (roleInfo == null)
        {
            roleInfo = new RoleInfo();
            mappings.put(httpMethod,roleInfo);
            if (allMethodsRoleInfo != null)
            {
                roleInfo.combine(allMethodsRoleInfo);
            }
        }
        if (roleInfo.isForbidden())
            return;

        //add in info from the constraint
        configureRoleInfo(roleInfo, mapping);
        
        if (roleInfo.isForbidden())
        {
            if (httpMethod.equals(ALL_METHODS))
            {
                mappings.clear();
                mappings.put(ALL_METHODS,roleInfo);
            }
        }
    }
