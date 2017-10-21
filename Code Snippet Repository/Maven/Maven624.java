    @Override
    public List<String> getSelectedProjects()
    {
        if ( selectedProjects == null )
        {
            selectedProjects = new ArrayList<>();
        }

        return selectedProjects;
    }
