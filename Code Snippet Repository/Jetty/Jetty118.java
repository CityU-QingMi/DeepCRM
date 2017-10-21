    protected boolean constraintsExist (List<ServletMapping> servletMappings, List<ConstraintMapping> constraintMappings)
    {
        boolean exists = false;

        //Check to see if the path spec on each constraint mapping matches a pathSpec in the servlet mappings.
        //If it does, then we should ignore the security annotations.
        for (ServletMapping mapping : servletMappings)
        {
            //Get its url mappings
            String[] pathSpecs = mapping.getPathSpecs();
            if (pathSpecs == null)
                continue;

            //Check through the constraints to see if there are any whose pathSpecs (url mappings)
            //match the servlet. If so, then we already have constraints defined for this servlet,
            //and we will not be processing the annotation (ie web.xml or programmatic override).
           for (int i=0; constraintMappings != null && i < constraintMappings.size() && !exists; i++)
           {
               for (int j=0; j < pathSpecs.length; j++)
               {
                   //TODO decide if we need to check the origin
                   if (pathSpecs[j].equals(constraintMappings.get(i).getPathSpec()))
                   {
                       exists = true;
                       break;
                   }
               }
           }
        }
        return exists;
    }
