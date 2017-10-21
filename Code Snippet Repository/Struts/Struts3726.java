    protected Attribute buildTemplateAttribute(TilesDefinition tilesDef) {
        // see tiles DigesterDefinitionsReader
        Attribute attribute = Attribute.createTemplateAttribute(getValueOrNull(tilesDef.template()));
        String templateExpression = getValueOrNull(tilesDef.templateExpression());
        Expression expression = Expression.createExpressionFromDescribedExpression(templateExpression);
        attribute.setExpressionObject(expression);
        attribute.setRole(getValueOrNull(tilesDef.role()));
        String templateType = getValueOrNull(tilesDef.templateType());
        if (templateType != null) {
            attribute.setRenderer(templateType);
        } else if (getValueOrNull(tilesDef.extend()) != null && templateType == null) {
            attribute.setRenderer(null);
        }
        return attribute;
    }
