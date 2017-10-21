    @Test
    public void buildDefinitonAllEmpty() {
        StrutsTilesAnnotationProcessor annotationProcessor = new StrutsTilesAnnotationProcessor();
        TilesDefinition tilesDefinition = annotationProcessor.findAnnotation(new TilesTestActionSingleAnnotationAllEmpty(), null);

        Definition definition = annotationProcessor.buildTilesDefinition(null, tilesDefinition);

        Assert.assertNotNull(definition);
        Assert.assertNull(definition.getName());
        Assert.assertNull(definition.getPreparer());
        Assert.assertNull(definition.getExtends());
        Attribute templateAttribute = definition.getTemplateAttribute();
        Assert.assertNull(templateAttribute.getValue());
        Assert.assertNull(templateAttribute.getRole());
        Assert.assertNull(templateAttribute.getExpressionObject());

        Attribute putAttribute = definition.getAttribute("put-attr");
        Assert.assertNotNull(putAttribute);
        Assert.assertNull(putAttribute.getValue());
        Assert.assertNull(putAttribute.getRenderer());
        Assert.assertNull(putAttribute.getRole());
        Assert.assertNull(putAttribute.getExpressionObject());

        Attribute listAttribute = definition.getAttribute("list-name");
        Assert.assertNull(listAttribute.getRole());
        List<Attribute> listValue = getListValue(listAttribute);
        Assert.assertEquals(2, listValue.size());

        Attribute addAttribute = listValue.get(0);
        Assert.assertNull(addAttribute.getRole());
        Assert.assertNull(addAttribute.getValue());
        Assert.assertNull(addAttribute.getRenderer());
        Assert.assertNull(addAttribute.getExpressionObject());

        Attribute addListAttribute = listValue.get(1);
        Assert.assertNull(addListAttribute.getRole());
        List<Attribute> addListValue = getListValue(addListAttribute);
        Assert.assertEquals(1, addListValue.size());
        Assert.assertNull(addListValue.get(0).getValue());

        Set<String> cascadedAttributeNames = definition.getCascadedAttributeNames();
        Assert.assertNull(cascadedAttributeNames);
    }
