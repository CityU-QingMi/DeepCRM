    public void setRoleClassNames (String[] classnames)
    {
        ArrayList<String> tmp = new ArrayList<String>();

        if (classnames != null)
            tmp.addAll(Arrays.asList(classnames));

        if (!tmp.contains(DEFAULT_ROLE_CLASS_NAME))
            tmp.add(DEFAULT_ROLE_CLASS_NAME);
        _roleClassNames = tmp.toArray(new String[tmp.size()]);
    }
