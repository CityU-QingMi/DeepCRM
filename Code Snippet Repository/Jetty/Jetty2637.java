    public static List<ConstraintMapping> getConstraintMappingsForPath(String pathSpec, List<ConstraintMapping> constraintMappings)
    {
        if (pathSpec == null || "".equals(pathSpec.trim()) || constraintMappings == null || constraintMappings.size() == 0)
            return Collections.emptyList();
        
        List<ConstraintMapping> mappings = new ArrayList<ConstraintMapping>();
        for (ConstraintMapping mapping:constraintMappings)
        {
            if (pathSpec.equals(mapping.getPathSpec()))
            {
               mappings.add(mapping);
            }
        }
        return mappings;
    }
