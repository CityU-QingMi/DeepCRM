    @Override
    protected AttributeEvaluatorFactory createAttributeEvaluatorFactory(
            ApplicationContext applicationContext,
            LocaleResolver resolver) {

        BasicAttributeEvaluatorFactory attributeEvaluatorFactory = new BasicAttributeEvaluatorFactory(new DirectAttributeEvaluator());
        attributeEvaluatorFactory.registerAttributeEvaluator(S2, createStrutsEvaluator());
        attributeEvaluatorFactory.registerAttributeEvaluator(I18N, createI18NEvaluator());
        attributeEvaluatorFactory.registerAttributeEvaluator(OGNL, createOGNLEvaluator());

        ELAttributeEvaluator elEvaluator = createELEvaluator(applicationContext);
        if (elEvaluator != null) {
            attributeEvaluatorFactory.registerAttributeEvaluator(EL, elEvaluator);
        }

        return attributeEvaluatorFactory;
    }
