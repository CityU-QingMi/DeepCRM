    private void addToInheritanceMap (String interfaceOrSuperClassName, String implementingOrExtendingClassName)
    {
      
        //As it is likely that the interfaceOrSuperClassName is already in the map, try getting it first
        Set<String> implementingClasses = _inheritanceMap.get(interfaceOrSuperClassName);
        //If it isn't in the map, then add it in, but test to make sure that someone else didn't get in 
        //first and add it
        if (implementingClasses == null)
        {
            implementingClasses = ConcurrentHashMap.newKeySet();
            Set<String> tmp = _inheritanceMap.putIfAbsent(interfaceOrSuperClassName, implementingClasses);
            if (tmp != null)
                implementingClasses = tmp;
        }
        
        implementingClasses.add(implementingOrExtendingClassName);
    }
