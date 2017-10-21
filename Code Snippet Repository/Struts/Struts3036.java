    public static Object getValueFromBeanInfoPropertyEditor(
            Class attrClass, String attrName, String attrValue,
            Class propertyEditorClass)
            throws JasperException {
        try {
            PropertyEditor pe = (PropertyEditor) propertyEditorClass.newInstance();
            pe.setAsText(attrValue);
            return pe.getValue();
        } catch (Exception ex) {
            throw new JasperException(
                    Localizer.getMessage("jsp.error.beans.property.conversion",
                            attrValue, attrClass.getName(), attrName,
                            ex.getMessage()));
        }
    }
