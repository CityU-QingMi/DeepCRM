    public boolean match(Class<?> clazz)
    {       
        try
        {
            Boolean byName = _patterns.isIncludedAndNotExcluded(clazz.getName());
            if (Boolean.FALSE.equals(byName))
                return byName; // Already excluded so no need to check location.
            URI location = TypeUtil.getLocationOfClass(clazz);
            Boolean byLocation = location == null ? null
                    : _locations.isIncludedAndNotExcluded(location);
            
            if (LOG.isDebugEnabled())
                LOG.debug("match {} from {} byName={} byLocation={} in {}",clazz,location,byName,byLocation,this);
            
            // Combine the tri-state match of both IncludeExclude Sets
            boolean included = byName==Boolean.TRUE || byLocation==Boolean.TRUE
                || (byName==null && !_patterns.hasIncludes() && byLocation==null && !_locations.hasIncludes());
            boolean excluded = byName==Boolean.FALSE || byLocation==Boolean.FALSE;
            return included && !excluded;
        }
        catch (Exception e)
        {
            LOG.warn(e);
        }
        return false;
    }
