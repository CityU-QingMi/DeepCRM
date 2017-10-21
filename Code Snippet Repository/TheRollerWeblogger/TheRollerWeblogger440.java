    public List<ThemeTemplateWrapper> getTemplates() throws WebloggerException {
        List<? extends ThemeTemplate> unwrapped = this.pojo.getTheme().getTemplates();
        List<ThemeTemplateWrapper> wrapped = new ArrayList<ThemeTemplateWrapper>(unwrapped.size());

        int i = 0;
        for (ThemeTemplate template : unwrapped) {
            wrapped.add(i,ThemeTemplateWrapper.wrap(template));
            i++;
        }
        return wrapped;
    }
