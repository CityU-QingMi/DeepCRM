    private ManagedList<BeanReference> extractBeanRefSubElements(Element parentElement, ParserContext parserContext){
        ManagedList<BeanReference> list = new ManagedList<>();
        list.setSource(parserContext.extractSource(parentElement));
        for (Element refElement : DomUtils.getChildElementsByTagName(parentElement, "ref")) {
            BeanReference reference;
            if (StringUtils.hasText("bean")) {
                reference = new RuntimeBeanReference(refElement.getAttribute("bean"),false);
                list.add(reference);
            }
			else if (StringUtils.hasText("parent")){
                reference = new RuntimeBeanReference(refElement.getAttribute("parent"),true);
                list.add(reference);
            }
			else {
                parserContext.getReaderContext().error("'bean' or 'parent' attribute is required for <ref> element",
                        parserContext.extractSource(parentElement));
            }
        }
        return list;
    }
