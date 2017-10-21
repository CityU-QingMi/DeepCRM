    public TextProvider makeTextProvider(Object object, TextProviderFactory textProviderFactory) {
        // the object argument passed through here will most probably be an ActionSupport descendant which does
        // implements TextProvider.
        if (object != null && object instanceof DelegatingValidatorContext) {
            return ((DelegatingValidatorContext) object).getTextProvider();
        }

        if ((object != null) && (object instanceof TextProvider)) {
            if (object instanceof CompositeTextProvider) {
                return (CompositeTextProvider) object;
            }
            return new CompositeTextProvider(new TextProvider[]{
                    ((TextProvider) object),
                    textProviderFactory.createInstance(object.getClass())
            });
        } else {
            return textProviderFactory.createInstance(
                    object != null ? object.getClass() : DelegatingValidatorContext.class);
        }
    }
