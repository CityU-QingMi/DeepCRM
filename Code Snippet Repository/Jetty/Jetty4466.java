    public boolean addComponent(String component)
    {
        if ((component == null) || (component.length() <= 0))
        {
            // nothing to add
            return false;
        }

        return addComponent(new File(component));
    }
