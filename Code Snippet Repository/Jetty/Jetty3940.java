    public String getClassNameForJsp (String jsp)
    {
        if (jsp == null)
            return null;

        String name = getNameOfJspClass(jsp);
        if (StringUtil.isBlank(name))
            return null;

        StringBuffer fullName = new StringBuffer();
        appendPath(fullName, getJspPackagePrefix());
        appendPath(fullName, getPackageOfJspClass(jsp));
        appendPath(fullName, name);
        return fullName.toString();
    }
