    public static boolean isInheritable (Package pack, Member member)
    {
        if (pack==null)
            return false;
        if (member==null)
            return false;
        
        int modifiers = member.getModifiers();
        if (Modifier.isPublic(modifiers))
            return true;
        if (Modifier.isProtected(modifiers))
            return true;
        if (!Modifier.isPrivate(modifiers) && pack.equals(member.getDeclaringClass().getPackage()))
            return true;
       
        return false;
    }
