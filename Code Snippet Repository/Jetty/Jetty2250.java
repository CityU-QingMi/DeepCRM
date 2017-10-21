    private void addInheritedTypes(Map<String, Set<String>> classMap,Set<String> names)
    {
        if (names == null || names.isEmpty())
            return;

        for (String s : names)
        {
            //add the name of the class
            addApplicableTypeName(s);

            //walk the hierarchy and find all types that extend or implement the class
            addInheritedTypes(classMap, (Set<String>)classMap.get(s));
        }
    }
