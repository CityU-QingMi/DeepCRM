    public void resolveClasses(WebAppContext context, Map<String, Set<String>> classMap) 
    {
        //We have already found the classes that directly have an annotation that was in the HandlesTypes
        //annotation of the ServletContainerInitializer. For each of those classes, walk the inheritance
        //hierarchy to find classes that extend or implement them.
        Set<String> annotatedClassNames = getAnnotatedTypeNames();
        if (annotatedClassNames != null && !annotatedClassNames.isEmpty())
        {
            for (String name : annotatedClassNames)
            {
                //add the class that has the annotation
                addApplicableTypeName(name);

                //find and add the classes that inherit the annotation               
                addInheritedTypes(classMap, (Set<String>)classMap.get(name));
            }
        }


        //Now we need to look at the HandlesTypes classes that were not annotations. We need to
        //find all classes that extend or implement them.
        if (getInterestedTypes() != null)
        {
            for (Class<?> c : getInterestedTypes())
            {
                if (!c.isAnnotation())
                {
                    //find and add the classes that implement or extend the class.
                    //but not including the class itself
                    addInheritedTypes(classMap, (Set<String>)classMap.get(c.getName()));
                }
            }
        }
    }
