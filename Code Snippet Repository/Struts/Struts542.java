    private CompositeTextProvider createTextProvider(Object o, ValidatorContext parent) {
        List<TextProvider> textProviders = new LinkedList<>();
        if (o instanceof TextProvider) {
            textProviders.add((TextProvider) o);
        } else {
            textProviders.add(textProviderFactory.createInstance(o.getClass()));
        }
        textProviders.add(parent);

        return new CompositeTextProvider(textProviders);
    }
