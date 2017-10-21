    public ContainerInitializer (ClassLoader loader, String toString)
    {
        Matcher m = Pattern.compile("ContainerInitializer\\{(.*),interested=(.*),applicable=(.*),annotated=(.*)\\}").matcher(toString);
        if (!m.matches())
            throw new IllegalArgumentException(toString);

        try
        {
            _target = (ServletContainerInitializer)loader.loadClass(m.group(1)).newInstance();
            String[] interested = StringUtil.arrayFromString(m.group(2));
            _interestedTypes = new Class<?>[interested.length];
            for (int i=0;i<interested.length;i++)
                _interestedTypes[i]=loader.loadClass(interested[i]);
            for (String s:StringUtil.arrayFromString(m.group(3)))
                _applicableTypeNames.add(s);
            for (String s:StringUtil.arrayFromString(m.group(4)))
                _annotatedTypeNames.add(s);
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException(toString, e);
        }
    }
