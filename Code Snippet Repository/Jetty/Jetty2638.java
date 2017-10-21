    public static List<ConstraintMapping> removeConstraintMappingsForPath(String pathSpec, List<ConstraintMapping> constraintMappings)
    {
        if (pathSpec == null || "".equals(pathSpec.trim()) || constraintMappings == null || constraintMappings.size() == 0)
            return Collections.emptyList();
        
        List<ConstraintMapping> mappings = new ArrayList<ConstraintMapping>();
        for (ConstraintMapping mapping:constraintMappings)
        {
            //Remove the matching mappings by only copying in non-matching mappings
            if (!pathSpec.equals(mapping.getPathSpec()))
            {
               mappings.add(mapping);
            }
        }
        return mappings;
    }
