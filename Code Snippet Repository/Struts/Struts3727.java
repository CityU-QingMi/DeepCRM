    protected Attribute buildPutListAttribute(TilesPutListAttribute putListAttribute) {
        ListAttribute attribute = new ListAttribute();
        attribute.setRole(getValueOrNull(putListAttribute.role()));
        attribute.setInherit(putListAttribute.inherit());
        for (TilesAddAttribute addAttribute : putListAttribute.addAttributes()) {
            attribute.add(buildAddAttribute(addAttribute));
        }
        for (TilesAddListAttribute addListAttribute : putListAttribute.addListAttributes()) {
            attribute.add(buildAddListAttribute(addListAttribute));
        }
        return attribute;
    }
